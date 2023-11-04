package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

public enum Operand {

    GREATER (">"){
        @Override
        public boolean evaluate(String[] value1, String[] value2){
            return Double.parseDouble(value1[0]) > Double.parseDouble(value2[0]);
        }
    },
    LESS ("<"){
        @Override
        public boolean evaluate(String[] value1, String[] value2){
            return Double.parseDouble(value1[0]) < Double.parseDouble(value2[0]);
        }
    },
    GREATER_OR_EQUAL (">="){
        @Override
        public boolean evaluate(String[] value1, String[] value2){
            return Double.parseDouble(value1[0]) >= Double.parseDouble(value2[0]);
        }
    },
    LESS_OR_EQUAL ("<="){
        @Override
        public boolean evaluate(String[] value1, String[] value2){
            return Double.parseDouble(value1[0]) <= Double.parseDouble(value2[0]);
        }
    },
    NOT_EQUAL ("!="){
        @Override
        public boolean evaluate(String[] value1, String[] value2){
            return Double.parseDouble(value1[0]) != Double.parseDouble(value2[0]);
        }
    },
    CONTAINS ("contains"){
        @Override
        /*
        Value 1 is the value of the task.
        Value 2 is the value of the criterion.
         */
        public boolean evaluate(String[] value1, String[] value2){
            if(value1.length==1&&value2.length==1) return value1[0].contains(value2[0]);
            for(String str2 : value2){
                for(String str1 : value1){
                    if(str1.contains(str2)) return true;
                }
            }
            return false;
        }
    },
    IS_PRIMITIVE ("IsPrimitive"){
        @Override
        public boolean evaluate(String[] value1, String[] value2){
            return false;
        }
    };

    private final String name;

    Operand(String name){ this.name = name;}

    public String toString() { return this.name;}

    public static Operand getOperand(String operand){
        return switch (operand) {
            case ">" -> GREATER;
            case "<" -> LESS;
            case ">=" -> GREATER_OR_EQUAL;
            case "<=" -> LESS_OR_EQUAL;
            case "!=" -> NOT_EQUAL;
            case "contains" -> CONTAINS;
            default -> null;
        };
    }

    /**
     * This method takes in two String arrays as input.
     * The first input is supposed to be the value of task.
     * The second input is supposed to be the value of criterion.
     * @param value1
     * @param value2
     * @return
     */
    public abstract boolean evaluate(String[] value1, String[] value2);
}