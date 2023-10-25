package hk.edu.polyu.comp.comp2021.tms.model;

import hk.edu.polyu.comp.comp2021.tms.model.TASK.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CheckAvailability {

    private static final Pattern NAMEPATTERN = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]{0,7}$");
    private static final Pattern DESCRIPTIONPATTERN = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9-]*$");

    /**
     * This method will check if the name of a task is legal.
     * @param name
     * @throws Exception
     */
    public static void checkName(String name) throws Exception{
        Matcher matcher = NAMEPATTERN.matcher(name);
        if(!matcher.matches())throw new Exception("The task name is illegal.");
    }

    /**
     * This method will check if the description of a task is legal.
     * @param description
     * @throws Exception
     */
    public static void checkDescription(String description) throws Exception{
        Matcher matcher = DESCRIPTIONPATTERN.matcher(description);
        if(!matcher.matches())throw new Exception("The task description is illegal.");
    }

    /**
     * Check the availability of the duration
     * @param duration
     * @throws Exception
     */
    public static void checkDuration(double duration) throws Exception{
        if(!(duration > 0)) throw new Exception("Duration must be positive.");
    }

    /**
     * Check if a task name has corresponding task object in the list.
     * It will return the task if it exists in the list.
     * @param storageLists
     * @param name
     * @return
     * @throws Exception
     */
    public static Task checkTaskExists(StorageLists storageLists, String name) throws Exception{
        Task task = storageLists.searchTaskList(name);
        if(task==null) throw new Exception("This task does not exist.");
        return task;
    }

    /**
     * Check is a task is already existing in the list.
     * @param storageLists
     * @param name
     * @throws Exception
     */
    public static void checkTaskAlreadyExists(StorageLists storageLists, String name) throws Exception{
        Task task = storageLists.searchTaskList(name);
        if(task != null) throw new Exception("This Task already exists.");
    }


}
