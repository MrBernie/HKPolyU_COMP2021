package hk.edu.polyu.comp.comp2021.tms.model.CRITERION;

import hk.edu.polyu.comp.comp2021.tms.model.TASK.Task;

public class NegatedCriterion extends Criterion{

    private Criterion criterion;
    final LogicOp logicOp = LogicOp.Negation;

    public NegatedCriterion(String name, Criterion criterion){
        super(name);
        this.criterion = criterion;
    }

    public String toString(){
        return "\nNegated Criterion: " +
                super.toString() +
                "\nNegated: " + criterion.name;
    }

    /**
     * Check if the task satisfies the criterion.
     * @param task task to be checked
     * @return true if the task satisfies the criterion
     */
    @Override
    public boolean check(Task task){
        return logicOp.evaluate(criterion.check(task),false);
    }
}
