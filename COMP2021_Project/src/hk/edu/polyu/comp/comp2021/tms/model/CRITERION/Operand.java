package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

public enum Operand {

    GREATER {
        public boolean evaluate(String[] value1, String[] value2){
            return Double.parseDouble(value1[0]) > Double.parseDouble(value2[0]);
        }
    },
    LESS {
        public boolean evaluate(String[] value1, String[] value2){
            return Double.parseDouble(value1[0]) < Double.parseDouble(value2[0]);
        }
    },
    GREATER_OR_EQUAL {
        public boolean evaluate(String[] value1, String[] value2){
            return Double.parseDouble(value1[0]) >= Double.parseDouble(value2[0]);
        }
    },
    LESS_OR_EQUAL {
        public boolean evaluate(String[] value1, String[] value2){
            return Double.parseDouble(value1[0]) <= Double.parseDouble(value2[0]);
        }
    },
    NOT_EQUAL {
        public boolean evaluate(String[] value1, String[] value2){
            return Double.parseDouble(value1[0]) != Double.parseDouble(value2[0]);
        }
    },
    CONTAINS {
        public boolean evaluate(String[] value1, String[] value2){
            boolean flag;
            for(String str2 : value2){
                flag = false;
                for(String str1 : value1){
                    if(str1.equals(str2)){
                        flag = true;
                        break;
                    }
                }
                if(!flag) return false;
            }
            return true;
        }
    };

    public static Operand getOperand(String operand){
        switch (operand){
            case ">": return GREATER;
            case "<": return LESS;
            case ">=": return GREATER_OR_EQUAL;
            case "<=": return LESS_OR_EQUAL;
            case "!=": return NOT_EQUAL;
            case "contains": return CONTAINS;
            default: return null;
        }
    }

    public abstract boolean evaluate(String[] value1, String[] value2);
}