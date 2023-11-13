package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

import hk.edu.polyu.comp.comp2021.tms.model.TASK.Task;

import java.io.Serializable;

public abstract class Criterion implements Serializable {

    protected String name;

    public Criterion(String name){
        this.name = name;
    }

    public String getName(){return name;}

    public String toString(){ return "\nName: " + this.name; }

    public abstract boolean check(Task task);

}
