package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

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

    public String getName(){ return super.name;}

    @Override
    public String toString(){
        StringBuilder strB = new StringBuilder();
        strB.append("\nBasic Criterion: ");
        strB.append(super.toString());
        strB.append("\nProperty: " + property.toString());
        strB.append("\nOperand: " + operand.toString());
        strB.append("\nValue: ");
        if(property!=Property.PREREQUISITE) strB.append(value[0]);
        else {
            for(String str : value) strB.append(str + ",");
        }
        return strB.toString();
    }

}
