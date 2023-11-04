package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

public enum Property {
    NAME("Name") ,
    DESCRIPTION("Description") ,
    DURATION("Duration") ,
    PREREQUISITE("Prerequisites") ,
    SUBTASKS("SubTasks") ,
    IS_PRIMITIVE("IsPrimitive");

    final private String name;

    Property(String name){ this.name = name;}

    public String toString(){ return this.name;}

    public static Property getProperty(String property){
        return switch (property) {
            case "name" -> NAME;
            case "description" -> DESCRIPTION;
            case "duration" -> DURATION;
            case "prerequisites" -> PREREQUISITE;
            case "subtasks" -> SUBTASKS;
            default -> null;
        };
    }
}
