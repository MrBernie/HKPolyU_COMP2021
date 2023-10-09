package TMS;
import TMS.TASK.*;

public class CheckAvailability {

    public static void checkName(String Name) throws Exception{
        //Todo
    }

    public static void checkDescription(String Description) throws Exception{
        //Todo
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
     * Check if a task name has corresponding task object in the list
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
     * Check is a task is already existing in the list
     * @param storageLists
     * @param name
     * @throws Exception
     */
    public static void checkTaskAlreadyExists(StorageLists storageLists, String name) throws Exception{
        Task task = storageLists.searchTaskList(name);
        if(task != null) throw new Exception("This Task already exists.");
    }

}
