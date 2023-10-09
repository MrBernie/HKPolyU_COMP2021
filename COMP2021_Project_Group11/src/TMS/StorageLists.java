package TMS;

import TMS.TASK.CRITERION.*;
import TMS.TASK.*;

import java.util.ArrayList;

public class StorageLists {

    protected ArrayList<Task> taskList;
    protected ArrayList<Criterion> criterionList;

    public StorageLists(){
        taskList = new ArrayList<Task>();
        criterionList = new ArrayList<Criterion>();
    }

    public Task searchTaskList(String name){
        for(Task t : taskList){
            if(t.getName().equals(name)) return t;
        }
        return null;
    }

    /*Primitive Task Operation*/
    public void createNewPrimitiveTask(String name, String description,
                                       double duration, String[] prerequisites) throws Exception {
        PrimitiveTask newPrimitiveTask = new PrimitiveTask(name, description,duration);
        setPrerequisites(newPrimitiveTask,prerequisites);
    }

    /**
     * Check if the prerequisite tasks of a primitive task exists in the task list
     * @param Task
     * @param prerequisites
     * @throws Exception
     */
    public void setPrerequisites(PrimitiveTask Task, String[] prerequisites) throws Exception{
        for (String str : prerequisites){
            Task temp = this.searchTaskList(str);
            if(temp==null) throw new Exception("Tasks in Prerequisite task does not exist.");
            Task.addPrerequisites(temp);
        }
    }


    /*Composite Task Operation*/
    public void createNewCompositeTask(String name, String description,
                                       String[] subtaskList) throws Exception{
        CompositeTask newCompositeTask = new CompositeTask(name, description);
        setSubTaskList(newCompositeTask, subtaskList);
    }

    /**
     * Check if the tasks in subTask List exists in the task list
     * @param Task
     * @param subTaskList
     * @throws Exception
     */
    public void setSubTaskList(CompositeTask Task, String[] subTaskList) throws Exception{
        for (String str : subTaskList){
            Task temp = this.searchTaskList(str);
            if(temp==null) throw new Exception("Tasks in SubTaskList task does not exist.");
            Task.addTask(temp);
        }
    }

    /* ************************************************************* */



}
