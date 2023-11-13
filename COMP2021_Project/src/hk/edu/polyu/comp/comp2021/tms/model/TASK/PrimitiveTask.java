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
        prerequisites = new ArrayList<>();
        isPrimitive = true;
        this.duration = duration;
    }

    public double getThisDuration() { return this.duration;}

    public void setDuration(double duration) { this.duration = duration; }

    public void addPrerequisites(Task task){
        prerequisites.add(task);
    }

    @Override
    public ArrayList<Task> getList(){return prerequisites;}

    @Override
    public String[] getNameArray(){
        String[] result = new String[prerequisites.size()];
        for(int i = 0;i< result.length;i++) result[i] = prerequisites.get(i).name;
        return result;
    }

    /**
     * This method return the sum of duration of its prerequisite.
     * Notice that this is different from the field "this.duration".
     * @return the sum of duration of its prerequisite.
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
        strB.append("\nPrimitive Task: ");
        strB.append(super.toString());
        strB.append("\nDuration: " + this.duration + "h");
        strB.append("\nPrerequisites: ");
        if(prerequisites.isEmpty()) strB.append("No Prerequisites.");
        for(Task t : prerequisites){
            strB.append(t.getName() + ",");
        }
        if(strB.charAt(strB.length()-1) == ',') strB.delete(strB.length()-1,strB.length());
        return strB.toString();
    }

}
