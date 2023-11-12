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
     * Check if this task is contained in the prerequisite or subtasks list of the input task.
     * For example, t1 contains t2, t3 and t4 as its subtask or prerequisite. Then t2.isContained(t1) is true.
     * @param task
     * @return
     */
    public boolean isContained(Task task){
        for (Task t : task.getList()) {
            if (t.getName().equals(this.name)) return true;
        }
        return false;
    }

    /**
     * Check if this task is contained in the family tree of the input task.
     * For example, t1 contains t2 and t3, t4 contains t1. Then t2 is part of t4.
     * @param task
     * @return
     */
    public boolean isPartOf(Task task){
        if(task.getList().isEmpty()) return false;
        for(Task t : task.getList()) if( this.name.equals(t.getName()) || this.isPartOf(t) ) return true;
        return false;
    }

    /**
     * Return Prerequisites List for primitive task.
     * Return SubTaskList for composite task.
     * @return
     */
    abstract public ArrayList<Task> getList();

    abstract public String[] getNameArray();

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
