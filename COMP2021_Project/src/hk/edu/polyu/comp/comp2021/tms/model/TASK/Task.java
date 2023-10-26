package hk.edu.polyu.comp.comp2021.tms.model.TASK;

import java.util.ArrayList;

public abstract class Task{
    protected String name;
    protected String description;
    protected boolean isPrimitive;

    public Task(String name, String description){
        this.name = name;
        this.description = description;
    }

    /**
     * Get the duration of this task and all of its prerequisites for PrimitiveTask.
     * Get the duration of the tasklist for CompositeTask.
     * @return
     */
    abstract public double getDuration();

    /**
     * Check if this task is contained in the prerequisite or subtasks of tasklist of the input task.
     * @param task
     * @return
     */
    abstract public boolean isContained(Task task);

    /**
     * Return Prerequisites List for primitive task.
     * Return SubTaskList for composite task.
     * @return
     */
    abstract public ArrayList<Task> getList();

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public boolean isPrimitive(){ return isPrimitive; }

    public String toString(){
        return "\nName: " + this.name +"\nDescription: " + this.description;
    }

    //Todo

}
