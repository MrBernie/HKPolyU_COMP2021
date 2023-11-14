package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

import hk.edu.polyu.comp.comp2021.tms.model.TASK.Task;

/**
 * This class represents a binary criterion.
 * A binary criterion is a criterion that is composed of two other criteria and a logic operand.
 */

public class BinaryCriterion extends Criterion{

    private Criterion criterion1;
    private Criterion criterion2;
    private LogicOp logicOp;


    /**
     * Constructor of Binary Criterion.
     * @param name name of the criterion
     * @param criterion1 first criterion
     * @param logicOp logic operand
     * @param criterion2 second criterion
     */
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
                "\nFirst Criterion: " + criterion1.getName() +
                "\nLogic Operand: " + logicOp.toString() +
                "\nFirst Criterion: " + criterion2.getName();
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
