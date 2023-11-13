package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

import hk.edu.polyu.comp.comp2021.tms.model.TASK.Task;

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

    @Override
    public String toString(){
        return "\nBinary Criterion: " +
                super.toString() +
                "\nFirst Criterion: " + criterion1.name +
                "\nLogic Operand: " + logicOp.toString() +
                "\nFirst Criterion: " + criterion2.name;
    }

    /**
     * Check if the task satisfies the criterion.
     * @param task task to be checked
     * @return true if the task satisfies the criterion
     */
    @Override
    public boolean check(Task task){
        return logicOp.evaluate(criterion1.check(task),criterion2.check(task));
    }

}
