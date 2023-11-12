package hk.edu.polyu.comp.comp2021.tms.model;

import hk.edu.polyu.comp.comp2021.tms.model.TASK.*;
import hk.edu.polyu.comp.comp2021.tms.model.CRITERION.*;

import java.io.Serializable;
import java.util.ArrayList;

class StorageLists implements Serializable {

    private ArrayList<Task> taskList;
    private ArrayList<Criterion> criterionList;

    public StorageLists(){
        taskList = new ArrayList<>();
        criterionList = new ArrayList<>();
        defineBasicCriterion("IsPrimitive",Property.IS_PRIMITIVE,Operand.IS_PRIMITIVE,
                new String[]{"Only for checking whether a task is primitive or not."});
    }

    /***********************************************************/
    /* Task List Operations */

    protected ArrayList<Task> getTaskList(){ return taskList; }
    protected ArrayList<Criterion> getCriterionList() { return criterionList; }

    /**
     * Search a task in the list by task name.
     * Return null if the task is not found.
     * @param name
     * @return
     */
    Task searchTaskList(String name){
        for(Task t : taskList) if(t.getName().equals(name)) return t;
        return null;
    }

    /**
     * Create new Primitive Task
     * @param name
     * @param description
     * @param duration
     * @param prerequisites
     * @throws Exception
     */
    void createNewPrimitiveTask(String name, String description,
                                       double duration, ArrayList<Task> prerequisites) throws Exception {
        PrimitiveTask newPrimitiveTask = new PrimitiveTask(name, description,duration);
        setPrerequisites(newPrimitiveTask,prerequisites);
        taskList.add(newPrimitiveTask);
    }

    /**
     * Create new Composite Task
     * @param name
     * @param description
     * @param subtaskList
     * @throws Exception
     */
    void createNewCompositeTask(String name, String description,
                                ArrayList<Task> subtaskList) throws Exception{
        CompositeTask newCompositeTask = new CompositeTask(name, description);
        setSubTaskList(newCompositeTask, subtaskList);
        taskList.add(newCompositeTask);
    }

    /**
     * Check if the prerequisite tasks of a primitive task exists in the task list
     * @param Task
     * @param prerequisites
     * @throws Exception
     */
    void setPrerequisites(PrimitiveTask Task, ArrayList<Task> prerequisites) throws Exception{
        if(prerequisites==null||prerequisites.size()==0) return;
        for (Task t : prerequisites){
            Task.addPrerequisites(t);
        }
    }

    /**
     * Check if the tasks in subTask List exists in the task list
     * @param Task
     * @param subTaskList
     * @throws Exception
     */
    void setSubTaskList(CompositeTask Task, ArrayList<Task> subTaskList) throws Exception{
        if(subTaskList==null||subTaskList.size()==0) return;
        for (Task t : subTaskList){
            Task.addTask(t);
        }
    }

    void deleteTask(Task task){
        for(Task t : taskList) if(t instanceof CompositeTask) t.getList().remove(task);
        taskList.remove(task);
    }

    String taskListString(){
        StringBuilder strB = new StringBuilder();
        for(Task t : this.taskList){
            strB.append(t.toString()+"\n");
        }
        return strB.toString();
    }

    /***************************************************************/
    /* Criterion List operations*/

    /**
     * Search a specific Criterion by name
     * @param name
     * @return
     */
    Criterion searchCriterionList(String name){
        for(Criterion c : criterionList) if(c.getName().equals(name)) return c;
        return null;
    }

    /**
     * Define a basic Criterion
     * @param name
     * @param property
     * @param operand
     * @param value
     */
    void defineBasicCriterion(String name, Property property, Operand operand, String[] value){
        BasicCriterion newBasicCriterion = new BasicCriterion(name, property, operand, value);
        criterionList.add(newBasicCriterion);
    }

    /**
     * Define a negated criterion
     * @param name
     * @param criterion
     */
    void defineNegatedCriterion(String name, Criterion criterion){
        NegatedCriterion newNegatedCriterion = new NegatedCriterion(name, criterion);
        criterionList.add(newNegatedCriterion);
    }

    /**
     * Define a binary criterion
     * @param name
     * @param criterion1
     * @param logicOp
     * @param criterion2
     */

    void defineBinaryCriterion(String name, Criterion criterion1, LogicOp logicOp, Criterion criterion2){
        BinaryCriterion newBinaryCriterion = new BinaryCriterion(name, criterion1, logicOp, criterion2);
        criterionList.add(newBinaryCriterion);
    }

    /**
     * Search through the Task List based on a criterion.
     * Return a list of tasks that meet the criterion.
     * @param criterion
     * @return
     */

    ArrayList<Task> search(Criterion criterion){
        ArrayList<Task> result = new ArrayList<>();
        for(Task t : taskList){
            if(criterion.check(t)) result.add(t);
        }
        return result;
    }

    String criterionListString(){
        StringBuilder strB = new StringBuilder();
        for(Criterion c : this.criterionList){
            strB.append(c.toString()+"\n");
        }
        return strB.toString();
    }

    void reset(){
        taskList = new ArrayList<>();
        criterionList = new ArrayList<>();
    }

    Byte toByte(){
        return Byte.parseByte(taskListString() + "\n" + criterionListString());
    }
}
