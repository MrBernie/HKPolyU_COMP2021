package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

public class NegatedCriterion extends Criterion{

    private Criterion criterion;
    final LogicOp logicOp = LogicOp.Negation;

    public NegatedCriterion(String name, Criterion criterion){
        super(name);
        this.criterion = criterion;
        this.isPrimitive = false;
    }
}
