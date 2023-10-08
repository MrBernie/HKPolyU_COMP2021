package TMS.TASK;
import java.lang.annotation.Inherited;
import java.util.*;

public class PrimitiveTask extends Task {

    private ArrayList<Task> prerequisites;
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

    public void addPrerequisites(Task task){
        prerequisites.add(task);
    }

    public boolean isContained(Task task){
        for (Task t : task.getList()) {
            if (t.name.equals(task.getName())) return true;
        }
        return false;
    }

    @Override
    public ArrayList<Task> getList(){return prerequisites;}

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

    //Todo

}
