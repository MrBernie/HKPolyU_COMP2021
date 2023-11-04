package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

import hk.edu.polyu.comp.comp2021.tms.model.TASK.Task;

public abstract class Criterion{

    protected String name;

    public Criterion(String name){
        this.name = name;
    }

    public String getName(){return name;}

    public String toString(){ return "\nName: " + this.name; }

    public abstract boolean check(Task task);

}
