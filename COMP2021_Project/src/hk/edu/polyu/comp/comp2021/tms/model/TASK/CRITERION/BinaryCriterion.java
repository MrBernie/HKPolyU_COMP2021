package hk.edu.polyu.comp.comp2021.tms.model.TASK.CRITERION;

public class BinaryCriterion extends Criterion{

    private Criterion[] criterion = new Criterion[2];
    private LogicOp logicOP;

    public BinaryCriterion(String name, LogicOp logicop, Criterion criterion0, Criterion criterion1){
        super(name);
        this.criterion[0] = criterion0;
        this.criterion[1] = criterion1;
        this.logicOP = logicop;
        this.isPrimitive = false;
    }

}
