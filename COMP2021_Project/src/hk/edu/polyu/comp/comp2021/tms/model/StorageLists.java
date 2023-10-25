package hk.edu.polyu.comp.comp2021.tms.model;

import hk.edu.polyu.comp.comp2021.tms.model.TASK.*;
import hk.edu.polyu.comp.comp2021.tms.model.TASK.CRITERION.*;

import java.util.ArrayList;

class StorageLists {

    protected ArrayList<Task> taskList;
    protected ArrayList<Criterion> criterionList;

    public StorageLists(){
        taskList = new ArrayList<>();
        criterionList = new ArrayList<>();
    }

    /**
     * Search a task in the list by task name.
     * Return null if the task is not found.
     * @param name
     * @return
     */
    public Task searchTaskList(String name){
        for(Task t : taskList){
            if(t.getName().equals(name)) return t;
        }
        return null;
    }

    /**
     * Create new Primitive Task
     * @param name
     * @param description
     * @param duration
     * @param prerequisites
     * @throws Exception
     */
    public void createNewPrimitiveTask(String name, String description,
                                       double duration, String[] prerequisites) throws Exception {
        PrimitiveTask newPrimitiveTask = new PrimitiveTask(name, description,duration);
        setPrerequisites(newPrimitiveTask,prerequisites);
        taskList.add(newPrimitiveTask);
    }

    /**
     * Check if the prerequisite tasks of a primitive task exists in the task list
     * @param Task
     * @param prerequisites
     * @throws Exception
     */
    public void setPrerequisites(PrimitiveTask Task, String[] prerequisites) throws Exception{
        if(prerequisites==null) return;
        for (String str : prerequisites){
            Task temp = this.searchTaskList(str);
            if(temp==null) throw new Exception("Tasks in Prerequisite task does not exist.");
            Task.addPrerequisites(temp);
        }
    }


    /**
     * Create new Composite Task
     * @param name
     * @param description
     * @param subtaskList
     * @throws Exception
     */
    public void createNewCompositeTask(String name, String description,
                                       String[] subtaskList) throws Exception{
        CompositeTask newCompositeTask = new CompositeTask(name, description);
        setSubTaskList(newCompositeTask, subtaskList);
        taskList.add(newCompositeTask);
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
