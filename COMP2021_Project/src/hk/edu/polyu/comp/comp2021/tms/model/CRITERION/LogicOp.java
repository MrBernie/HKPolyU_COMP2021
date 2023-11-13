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

    /**
     * Get the logic operator by its name.
     * @param lOp logic operator name
     * @return logic operator
     */
    public static LogicOp getLogicOp(String lOp){
        return switch (lOp) {
            case "&&" -> AND;
            case "||" -> OR;
            default -> null;
        };
    }

    /**
     * Evaluate the logic operator.
     * @param criterionResult1 result of the first criterion
     * @param criterionResult2 result of the second criterion
     * @return result of the logic operator
     */
    public abstract boolean evaluate(boolean criterionResult1, boolean criterionResult2);
}
