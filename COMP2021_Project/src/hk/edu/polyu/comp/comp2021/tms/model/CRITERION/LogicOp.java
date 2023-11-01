package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

public enum LogicOp {
    AND ("&&"),
    OR ("||"),
    Negation ("!");

    private final String name;

    LogicOp(String name){this.name = name;}

    public String toString(){return this.name;}

    public static LogicOp getLogicOp(String lOp){
        switch (lOp){
            case "&&": return AND;
            case "||": return OR;
            default: return null;
        }
    }
}
