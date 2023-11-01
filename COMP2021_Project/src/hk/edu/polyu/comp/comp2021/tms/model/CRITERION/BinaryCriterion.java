package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

public class BinaryCriterion extends Criterion{

    private Criterion criterion1;
    private Criterion criterion2;
    private LogicOp logicOp;

    public BinaryCriterion(String name, Criterion criterion1, LogicOp logicOp , Criterion criterion2){
        super(name);
        this.criterion1 = criterion1;
        this.criterion2 = criterion2;
        this.logicOp = logicOp;
    }

    public String toString(){
        StringBuilder strB = new StringBuilder();
        strB.append("\nBinary Criterion: ");
        strB.append(super.toString());
        strB.append("\nFirst Criterion: " + criterion1.name);
        strB.append("\nLogic Operand: " + logicOp.toString());
        strB.append("\nFirst Criterion: " + criterion2.name);
        return strB.toString();
    }

}
