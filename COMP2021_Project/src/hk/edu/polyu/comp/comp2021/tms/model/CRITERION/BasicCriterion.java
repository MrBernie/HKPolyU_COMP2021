package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

public class BasicCriterion extends Criterion{
    private Property property;
    private Operand operand;
    private double value;

    public BasicCriterion(String name, Property property, Operand operand, double value){
        super(name);
        this.property = property;
        this.operand = operand;
        this.value = value;
        this.isPrimitive = true;
    }


    //Todo

}
