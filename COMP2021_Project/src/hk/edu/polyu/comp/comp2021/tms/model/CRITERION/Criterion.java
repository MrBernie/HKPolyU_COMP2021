package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

public abstract class Criterion{

    protected String name;

    public Criterion(String name){
        this.name = name;
    }

    public String getName(){return name;}

    public String toString(){ return "\nName: " + this.name; }

}
