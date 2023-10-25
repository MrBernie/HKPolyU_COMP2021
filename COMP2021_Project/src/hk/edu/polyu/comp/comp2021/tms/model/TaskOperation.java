package hk.edu.polyu.comp.comp2021.tms.model;
import hk.edu.polyu.comp.comp2021.tms.model.TASK.*;

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
    public static void createSimpleTask (StorageLists storageLists, String name, String description,
                                           double duration, String[] prerequisites) throws Exception {
        CheckAvailability.checkName(name);
        CheckAvailability.checkTaskAlreadyExists(storageLists, name);
        CheckAvailability.checkDescription(description);
        CheckAvailability.checkDuration(duration);
        storageLists.createNewPrimitiveTask(name, description, duration, prerequisites);
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
    public static void createCompositeTask(StorageLists storageLists, String name, String description,
                                           String[] subTaskList) throws Exception{
        CheckAvailability.checkName(name);
        CheckAvailability.checkTaskAlreadyExists(storageLists, name);
        CheckAvailability.checkDescription(description);
        storageLists.createNewCompositeTask(name, description, subTaskList);
    }

    /**
     * Req 3
     * Delete Task
     * @param storageLists
     * @param name
     * @return
     * @throws Exception
     */
    public static void deleteTask(StorageLists storageLists, String name) throws Exception{
        Task task = storageLists.searchTaskList(name);
        if(task == null) throw new Exception("This Task does not exist.");
        for(Task t : storageLists.taskList){
            if(task.isContained(t)) throw new Exception("This Task is required by other tasks.");
        }
        storageLists.taskList.remove(task);
    }

    /**
     * Req 4
     * Change Property
     * @param property
     * @param newValue
     * @throws Exception
     */
    public static void setProperty(StorageLists storageLists, String name,
                                   String property, String[] newValue) throws Exception{
        Task task = CheckAvailability.checkTaskExists(storageLists, name);
        switch (property.toLowerCase()){
            case "name":
                task.setName(newValue[0]);
                break;
            case "description":
                task.setDescription(newValue[0]);
                break;
            case "duration":
                if(!task.isPrimitive()) throw new Exception("Cannot set duration for composite task.");
                CheckAvailability.checkDuration(Double.parseDouble(newValue[0]));
                PrimitiveTask primitiveTask = (PrimitiveTask) task;
                primitiveTask.setDuration(Double.parseDouble(newValue[0]));
                break;
            case "prerequisites":
                if(!task.isPrimitive()) throw new Exception("Cannot set prerequisites for composite task.");
                storageLists.setPrerequisites( (PrimitiveTask)task, newValue);
                break;
            case "subtasks":
                if(task.isPrimitive()) throw new Exception("Cannot set subtasks for primitive task.");
                storageLists.setSubTaskList( (CompositeTask)task, newValue);
                break;
            default:
                throw new Exception("Invalid Property input.");
        }
    }

    /**
     * Req 5
     * Print the information of a task.
     * @param name
     * @throws Exception
     */
    public static String printTask(StorageLists storageLists, String name) throws Exception{
        Task task = CheckAvailability.checkTaskExists(storageLists, name);
        return task.toString();
    }

    /**
     * Req 6
     * Print the information of all task.
     * @param storageLists
     */
    public static String printAllTasks(StorageLists storageLists){
        StringBuilder strB = new StringBuilder();
        for(Task t : storageLists.taskList){
            strB.append(t.toString()+"\n");
        }
        return strB.toString();
    }

    /**
     * Req 7
     * Print the duration of a composite task
     * @param storageLists
     * @param name
     */
    public static String reportDuration(StorageLists storageLists, String name) throws Exception{
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
    public static String reportEarliestFinishTime(StorageLists storageLists, String name) throws Exception{
        Task task = CheckAvailability.checkTaskExists(storageLists,name);
        if(!task.isPrimitive()) throw new Exception("Reporting the earliest finish time can only be applied to Simple Task.)");
        return "\nThe Earliest finish time of the Simple Task \""+task.getName()+"\" is " +task.getDuration()+"h.";
    }

}
