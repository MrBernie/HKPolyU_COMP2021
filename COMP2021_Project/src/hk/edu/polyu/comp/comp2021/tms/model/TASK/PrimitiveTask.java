package hk.edu.polyu.comp.comp2021.tms.model.TASK;

import java.util.ArrayList;

public class PrimitiveTask extends Task {

    private ArrayList<Task> prerequisites;
    /**
     * This duration field only denote the duration of this task itself.
     */
    private double duration;

    public PrimitiveTask(String name, String description, double duration){
        super(name, description);
        prerequisites = new ArrayList<Task>();
        isPrimitive = true;
        this.duration = duration;
    }

    /**
     * Only return the duration of this task.
     * @return
     */
    public double getThisDuration(){
        return this.duration;
    }

    public void setDuration(double duration) { this.duration = duration; }

    public void addPrerequisites(Task task){
        prerequisites.add(task);
    }

    public boolean isContained(Task task){
        for (Task t : task.getList()) {
            if (t.getName().equals(this.name)) return true;
        }
        return false;
    }

    @Override
    public ArrayList<Task> getList(){return prerequisites;}

    /**
     * This method return the sum of duration of its prerequisite.
     * Notice that this is different from the field "Duration".
     * @return
     */
    @Override
    public double getDuration() {
        if(prerequisites.isEmpty()) return this.duration;
        double duration = 0;
        for(Task t : prerequisites) {
            double subTaskDuration = t.getDuration();
            if (duration < subTaskDuration) duration = subTaskDuration;
        }
        return (duration + this.duration);
    }

    @Override
    public String toString(){
        StringBuilder strB = new StringBuilder();
        strB.append(super.toString());
        strB.append("\nDuration: " + this.duration + "h");
        strB.append("\nPrerequisites: ");
        if(prerequisites.isEmpty()) strB.append("No Prerequisites.");
        for(Task t : prerequisites){
            strB.append(t.getName() + ",");
        }
        return strB.toString();
    }

    //Todo

}
