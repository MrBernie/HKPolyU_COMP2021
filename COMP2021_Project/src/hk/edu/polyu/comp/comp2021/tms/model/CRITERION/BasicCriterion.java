package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

import hk.edu.polyu.comp.comp2021.tms.model.TASK.*;

public class BasicCriterion extends Criterion{
    private Property property;
    private Operand operand;
    private String[] value;

    public BasicCriterion(String name, Property property, Operand operand, String[] value){
        super(name);
        this.property = property;
        this.operand = operand;
        this.value = value;
    }

    @Override
    public String toString(){
        StringBuilder strB = new StringBuilder();
        strB.append("\nBasic Criterion: ");
        strB.append(super.toString());
        strB.append("\nProperty: " + property.toString());
        strB.append("\nOperand: " + operand.toString());
        strB.append("\nValue: ");
        if(property!=Property.PREREQUISITE&&property!=Property.SUBTASKS) strB.append("\""+value[0]+"\"");
        else {
            for(String str : value) strB.append(str + ",");
        }
        return strB.toString();
    }

    /**
     * The basic checking function.
     * @param task task to be checked
     * @return true if the task satisfies the criterion
     */
    @Override
    public boolean check(Task task){
        if(this.operand==Operand.IS_PRIMITIVE) return task.isPrimitive();
        switch (property){
            case NAME -> {
                return operand.evaluate(new String[]{task.getName()} , value);
            }
            case DURATION -> {
                if(task instanceof PrimitiveTask){
                    return operand.evaluate(new String[]{String.valueOf(((PrimitiveTask) task).getThisDuration())} , value);
                }
                else return false;
            }
            case DESCRIPTION -> {
                return operand.evaluate(new String[]{task.getDescription()} , value);
            }
            case SUBTASKS -> {
                if(task instanceof CompositeTask) return operand.evaluate(task.getNameArray() , value);
                else return false;
            }
            case PREREQUISITE -> {
                if(task instanceof PrimitiveTask) return operand.evaluate(task.getNameArray() , value);
                else return false;
            }
            default -> {
                return false;
            }
        }
    }

}
