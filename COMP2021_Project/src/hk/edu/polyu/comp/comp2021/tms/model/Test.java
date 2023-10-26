package hk.edu.polyu.comp.comp2021.tms.model;

import java.util.ArrayList;
import java.util.List;

public class Test {

    /**
     * Test methods here.
     * @param args
     */
    public static void main(String[] args){
        StorageLists sList = new StorageLists();
        List<Exception> eList = new ArrayList<Exception>();
        try {
            TaskOperation.createSimpleTask(sList,"Task1","t1-simple-task","2.3",null);
            TaskOperation.createSimpleTask(sList,"Task3","t3-simple-task","10",null);
            String[] property = {"3.5"};
            String[] preRofTask1 = {"Task3"};
            TaskOperation.setProperty(sList,"Task1","Prerequisites",preRofTask1);
            String[] subtask = {"Task1","Task3"};
            TaskOperation.createCompositeTask(sList,"Task2","t2-composite-task",subtask);
            System.out.print(TaskOperation.printAllTasks(sList));

            System.out.print(TaskOperation.reportDuration(sList,"Task2"));
            System.out.print(TaskOperation.reportEarliestFinishTime(sList,"Task1"));
            System.out.print(TaskOperation.reportEarliestFinishTime(sList,"Task3"));
        }
        catch (Exception e) {
            eList.add(e);
        }
        finally{
            if(!eList.isEmpty()){
                for(Exception e : eList) System.out.println(e.getMessage());
            }
        }
    }
}
