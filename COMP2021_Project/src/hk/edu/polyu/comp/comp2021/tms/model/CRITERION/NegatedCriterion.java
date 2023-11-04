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
        String str = "\nNegated Criterion: " +
                super.toString() +
                "\nNegated: " + criterion.name;
        return str;
    }

    @Override
    /**
     * Return the result of boolean calculation of the criterion.
     */
    public boolean check(Task task){
        return logicOp.evaluate(criterion.check(task),false);
    }
}
