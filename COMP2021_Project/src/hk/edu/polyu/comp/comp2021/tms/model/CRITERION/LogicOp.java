package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

public enum LogicOp {
    AND ("&&") {
        @Override
        public boolean evaluate(boolean criterionResult1, boolean criterionResult2) {
            return criterionResult1 && criterionResult2;
        }
    },
    OR ("||") {
        @Override
        public boolean evaluate(boolean criterionResult1, boolean criterionResult2){
            return criterionResult1 || criterionResult2;
        }
    },
    Negation ("!"){
        @Override
        // The second input is not used here.
        public boolean evaluate(boolean criterionResult1, boolean criterionResult2){
            return !criterionResult1;
        }
    };

    private final String name;

    LogicOp(String name){this.name = name;}

    public String toString(){return this.name;}

    public static LogicOp getLogicOp(String lOp){
        return switch (lOp) {
            case "&&" -> AND;
            case "||" -> OR;
            default -> null;
        };
    }

    public abstract boolean evaluate(boolean criterionResult1, boolean criterionResult2);
}
