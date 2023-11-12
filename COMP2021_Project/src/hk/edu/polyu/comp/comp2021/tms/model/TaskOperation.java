package hk.edu.polyu.comp.comp2021.tms.model;
import hk.edu.polyu.comp.comp2021.tms.model.CRITERION.*;
import hk.edu.polyu.comp.comp2021.tms.model.TASK.*;

import java.util.Locale;

class TaskOperation {

    /**
     * Req 1
     * Create new Primitive Task
     * @param storageLists
     * @param name
     * @param description
     * @param duration
     * @param prerequisites
     * @throws Exception
     */
    static String createSimpleTask(StorageLists storageLists, String name, String description,
                                           String duration, String[] prerequisites) throws Exception {
        CheckAvailability.checkName(name);
        CheckAvailability.checkTaskAlreadyExists(storageLists, name);
        CheckAvailability.checkDescription(description);
        Double dur = CheckAvailability.checkDuration(duration);
        storageLists.createNewPrimitiveTask(name, description, dur, prerequisites);
        return "Simple task \""+name+"\" has been created successfully.";
    }

    /**
     * Req 2
     * Create new Composite Task
     * @param storageLists
     * @param name
     * @param description
     * @param subTaskList
     * @throws Exception
     */
    static String createCompositeTask(StorageLists storageLists, String name, String description,
                                           String[] subTaskList) throws Exception{
        CheckAvailability.checkName(name);
        CheckAvailability.checkTaskAlreadyExists(storageLists, name);
        CheckAvailability.checkDescription(description);
        storageLists.createNewCompositeTask(name, description, subTaskList);
        return "Composite task \""+name+"\" has been created successfully.";
    }

    /**
     * Req 3
     * Delete Task
     * @param storageLists
     * @param name
     * @return
     * @throws Exception
     */
    static String deleteTask(StorageLists storageLists, String name) throws Exception{
        Task task = storageLists.searchTaskList(name);
        if(task == null) throw new Exception("This Task does not exist.");
        for(Task t : storageLists.taskList){
            if(task.isContained(t)) throw new Exception("This Task is required by other tasks.");
        }
        storageLists.taskList.remove(task);
        return "Task \""+name+"\" has been deleted successfully.";
    }

    /**
     * Req 4
     * Change Property
     * @param property
     * @param newValue
     * @throws Exception
     */
    static String setProperty(StorageLists storageLists, String name,
                                   String property, String[] newValue) throws Exception{
        Task task = CheckAvailability.checkTaskExists(storageLists, name);
        Property pro = CheckAvailability.checkProperty(property.toLowerCase());
        switch (pro) {
            case NAME -> task.setName(newValue[0]);
            case DESCRIPTION -> task.setDescription(newValue[0]);
            case DURATION -> {
                if (!task.isPrimitive()) throw new Exception("Cannot set duration for composite task.");
                PrimitiveTask primitiveTask = (PrimitiveTask) task;
                primitiveTask.setDuration(CheckAvailability.checkDuration(newValue[0]));
            }
            case PREREQUISITE -> {
                if (!task.isPrimitive()) throw new Exception("Cannot set prerequisites for composite task.");
                storageLists.setPrerequisites((PrimitiveTask) task, newValue);
            }
            case SUBTASKS -> {
                if (task.isPrimitive()) throw new Exception("Cannot set subtasks for primitive task.");
                storageLists.setSubTaskList((CompositeTask) task, newValue);
            }
            default -> throw new Exception("Invalid Property input.");
        }
        return "Task \""+name+"\"'s property \""+ property+"\" has been set successfully.";
    }

    /**
     * Req 5
     * Print the information of a task.
     * @param name
     * @throws Exception
     */
    static String printTask(StorageLists storageLists, String name) throws Exception{
        Task task = CheckAvailability.checkTaskExists(storageLists, name);
        return task.toString();
    }

    /**
     * Req 6
     * Print the information of all task.
     * @param storageLists
     */
    static String printAllTasks(StorageLists storageLists){
        StringBuilder strB = new StringBuilder("\nStart printing all tasks...\n");
        if(storageLists.taskList.isEmpty()) strB.append("There is no tasks currently...");
        strB.append(storageLists.taskListString());
        return strB.toString();
    }

    /**
     * Req 7
     * Print the duration of a composite task
     * @param storageLists
     * @param name
     */
    static String reportDuration(StorageLists storageLists, String name) throws Exception{
        Task task = CheckAvailability.checkTaskExists(storageLists,name);
        if(task.isPrimitive()) throw new Exception("Reporting Duration can only be applied to Composite Task.");
        return "\nThe Duration of the Composite Task \""+task.getName()+"\" is " +task.getDuration()+"h.";
    }

    /**
     * Req 8
     * Print the earliest finish time of a simple task
     * @param storageLists
     * @param name
     * @return
     * @throws Exception
     */
    static String reportEarliestFinishTime(StorageLists storageLists, String name) throws Exception{
        Task task = CheckAvailability.checkTaskExists(storageLists,name);
        if(!task.isPrimitive())
            throw new Exception("Reporting the earliest finish time can only be applied to Simple Task.");
        return "\nThe Earliest finish time of the Simple Task \""+task.getName()+"\" is " +task.getDuration()+"h.";
    }

}
