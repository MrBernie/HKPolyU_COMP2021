package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

public class NegatedCriterion extends Criterion{

    private Criterion criterion;
    final LogicOp logicOp = LogicOp.Negation;

    public NegatedCriterion(String name, Criterion criterion){
        super(name);
        this.criterion = criterion;
    }

    public String toString(){
        StringBuilder strB = new StringBuilder();
        strB.append("\nNegated Criterion: ");
        strB.append(super.toString());
        strB.append("\nNegated: " + criterion.name);
        return strB.toString();
    }
}
