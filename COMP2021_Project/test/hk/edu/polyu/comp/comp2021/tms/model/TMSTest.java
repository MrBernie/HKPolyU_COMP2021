package hk.edu.polyu.comp.comp2021.tms.model;

import org.junit.Before;
import org.junit.Test;

public class TMSTest {

//    @Test
//    public void testTMSConstructor(){
//        TMS tms = new TMS();
//        tms.run();
//    }

    String[] taskCommands = {
            "asdoufihaoushe",
            "createsimpletask weriuu",
            "createcompositetask 123 49",
            "createsimpletask t1 t1 4 ,",
            "createsimpletask t2 t2 3 t1",
            "createsimpletask t3 t3 6 t2",
            "createsimpletask t4 t4 -34 ,",
            "changetask t1 prerequisites t3",
            "createcompositetask t4 t4 t1,t2,t3",
            "createsimpletask t5 t5 10 t2,t4",
            "deletetask t1",
            "deletetask t4",
            "reportearliestfinishtime t5",
            "deletetask t5",
            "changetask t1 name t0",
            "printtask t5",
            "printtask t2",
            "printalltasks",
            "changetask t0 description t0",
            "changetask t0 duration 12",
            "reportduration t4",
            "changetask t3 prerequisites t0",
            "store data.bin"
    };

    TMS tms = new TMS();

    @Test
    public void testTask(){

        for(String command : taskCommands){
            try {
                System.out.println(tms.operation(command.split(" ")));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

}