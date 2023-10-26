package hk.edu.polyu.comp.comp2021.tms.model.TASK;

import java.util.ArrayList;

public class CompositeTask extends Task{

    private ArrayList<Task> subTaskList;

    public CompositeTask(String name, String description){
        super(name, description);
        subTaskList = new ArrayList<Task>();
        this.subTaskList = new ArrayList<>();
        isPrimitive = false;
    }

    public void addTask(Task task){subTaskList.add(task);}

    public boolean isContained(Task task){
        for(Task t: task.getList()) {
            if (t.getName().equals(this.name)) return true;
        }
        return false;
    }

    @Override
    public ArrayList<Task> getList(){return subTaskList;}

    @Override
    public double getDuration() {
        double duration = 0;
        for(Task t : subTaskList) {
            double subTaskDuration = t.getDuration();
            if (duration < subTaskDuration) duration = subTaskDuration;
        }
        return duration;
    }

    @Override
    public String toString(){
        StringBuilder strB = new StringBuilder();
        strB.append(super.toString());
        strB.append("\nSubtasks: ");
        if(subTaskList.isEmpty()) strB.append("No Subtasks.");
        for(Task t : subTaskList){
            strB.append(t.getName() + ",");
        }
        return strB.toString();
    }

    //Todo
}
