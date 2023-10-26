package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

public enum Property {
    NAME ,
    DESCRIPTION ,
    DURATION ,
    PREREQUISITE ;

    public static final String[] PROPERTYSYMBOL = {"name","description","duration","prerequisite"};

    public static Property getProperty(String property){
        switch (property){
            case "name" : return NAME;
            case "description" : return DESCRIPTION;
            case "duration" : return DURATION;
            case "prerequisite" : return PREREQUISITE;
            default : return null;
        }
    }
}
