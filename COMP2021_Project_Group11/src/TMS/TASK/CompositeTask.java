package TMS.TASK;
import java.util.*;

public class CompositeTask extends Task{

    private ArrayList<Task> subTaskList;

    public CompositeTask(String name, String description){
        super(name, description);
        this.subTaskList = new ArrayList<Task>();
        isPrimitive = false;
    }

    public void addTask(Task task){subTaskList.add(task);}

    public boolean isContained(Task task){
        for(Task t: task.getList()) {
            if (t.name.equals(task.getName())) return true;
        }
        return true;
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
    public String printInfo(){
        StringBuilder strB = new StringBuilder();
        strB.append(super.printInfo());
        for(Task t : subTaskList){
            strB.append(t.getName() + ",");
        }
        return strB.toString();
    }

    //Todo
}
