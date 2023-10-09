package TMS;
import TMS.TASK.*;

public class TaskOperation {

    /**
     * Req 1
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
        return;
    }

    /**
     * Req 2
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
        return;
    }

    /**
     * Req 3
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
     * @param property
     * @param newValue
     * @throws Exception
     */
    public static void setProperty(StorageLists storageLists, String name,
                                   String property, String[] newValue) throws Exception{
        Task task = CheckAvailability.checkTaskExists(storageLists, name);
        switch (property){
            case "name":
                task.setName(newValue[0]);
                break;
            case "description":
                task.setDescription(newValue[0]);
                break;
            case "duration":
                if(task.isPrimitive()==false) throw new Exception("Cannot set duration for composite task.");
                CheckAvailability.checkDuration(Double.valueOf(newValue[0]));
                PrimitiveTask primitiveTask = (PrimitiveTask) task;
                primitiveTask.setDuration(Double.valueOf(newValue[0]));
                break;
            case "prerequisites":
                if(task.isPrimitive()==false) throw new Exception("Cannot set prerequisites for composite task.");
                storageLists.setPrerequisites( (PrimitiveTask)task, newValue);
                break;
            case "subtasks":
                if(task.isPrimitive()==true) throw new Exception("Cannot set subtasks for primitive task.");
                storageLists.setSubTaskList( (CompositeTask)task, newValue);
                break;
            default:
                throw new Exception("Invalid pProperty input.");
        }
    }

    /**
     * Req 5
     * @param name
     * @throws Exception
     */
    public static void printTask(StorageLists storageLists, String name) throws Exception{
        Task task = CheckAvailability.checkTaskExists(storageLists, name);
        System.out.println(task.printInfo());
    }

    /**
     * Req 6
     * @param storageLists
     */
    public static void printAllTasks(StorageLists storageLists){
        for(Task t : storageLists.taskList){
            t.printInfo();
        }
    }

    /**
     * Req 7
     * @param storageLists
     * @param name
     */
    public static void reportDuration(StorageLists storageLists, String name){

    }

}
