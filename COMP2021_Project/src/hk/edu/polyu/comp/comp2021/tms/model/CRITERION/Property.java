package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

public enum Property {
    NAME("Name") ,
    DESCRIPTION("Description") ,
    DURATION("Duration") ,
    PREREQUISITE("Prerequisites") ,
    SUBTASKS("SubTasks");

    final private String name;

    Property(String name){ this.name = name;}

    public String toString(){ return this.name;}

    public static Property getProperty(String property){
        switch (property){
            case "name" : return NAME;
            case "description" : return DESCRIPTION;
            case "duration" : return DURATION;
            case "prerequisites" : return PREREQUISITE;
            case "subtasks" : return SUBTASKS;
            default : return null;
        }
    }
}
