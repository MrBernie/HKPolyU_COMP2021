package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

public enum Operand {

    GREATER (">"){
        public boolean evaluate(String[] value1, String[] value2){
            return Double.parseDouble(value1[0]) > Double.parseDouble(value2[0]);
        }
    },
    LESS ("<"){
        public boolean evaluate(String[] value1, String[] value2){
            return Double.parseDouble(value1[0]) < Double.parseDouble(value2[0]);
        }
    },
    GREATER_OR_EQUAL (">="){
        public boolean evaluate(String[] value1, String[] value2){
            return Double.parseDouble(value1[0]) >= Double.parseDouble(value2[0]);
        }
    },
    LESS_OR_EQUAL ("<="){
        public boolean evaluate(String[] value1, String[] value2){
            return Double.parseDouble(value1[0]) <= Double.parseDouble(value2[0]);
        }
    },
    NOT_EQUAL ("!="){
        public boolean evaluate(String[] value1, String[] value2){
            return Double.parseDouble(value1[0]) != Double.parseDouble(value2[0]);
        }
    },
    CONTAINS ("contains"){
        /*
        Value 1 is the value of the criteria.
        Value 2 is the value of the tasks.
         */
        public boolean evaluate(String[] value1, String[] value2){
            if(value1.length==1&&value2.length==1) return value2[0].contains(value1[0]);
            for(String str2 : value2){
                for(String str1 : value1){
                    if(str1.equals(str2)) return true;
                }
            }
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

    public abstract boolean evaluate(String[] value1, String[] value2);
}