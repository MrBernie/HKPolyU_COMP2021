package hk.edu.polyu.comp.comp2021.tms.model;

import org.junit.Before;
import org.junit.Test;

/**
 * This class is used to test the TMS class.
 */
public class TMSTest {

    private final String[] taskCommands = {
            "asdoufihaoushe",
            "createsimpletask weriuu",
            "createcompositetask 123 49",
            "createsimpletask t1 t1 3 t3",
            "createsimpletask t1 t1 4 ,",
            "createsimpletask t1 t1 3 ,",
            "createsimpletask t2 t2 3 t1",
            "createsimpletask t3 t3 -8 t1",
            "createsimpletask t3 t3 6 t2",
            "changetask t1 prerequisites t3",
            "createsimpletask t4 t4 -34 ,",
            "changetask t1 prerequisites t3",
            "createcompositetask t4 t4 t1,t2,t3",
            "changetask t4 subtasks t1,t2",
            "createsimpletask t5 t5 10 t2,t4",
            "deletetask t1",
            "deletetask t4",
            "reportearliestfinishtime t5",
            "deletetask t5",
            "deletetasj t10",
            "changetask t1 name t0",
            "printtask t5",
            "printtask t2",
            "printalltasks",
            "changetask t0 description t0",
            "changetask t0 duration 12",
            "reportduration t4",
            "changetask t3 prerequisites t0",
            "store ../data1.bin",
            "quit"
    };

    private final String[] criteriaCommands = {
            "load ../data1.bin",
            "definebasiccriterion c1 name contains t",
            "definebasiccriterion 23408 eufoaiwe awoe8",
            "definebasiccriterion c1 name contains \"n\"",
            "definebasiccriterion c2 description contains \"t\"",
            "search c2",
            "definebasiccriterion c3 duration <= 10",
            "search c3",
            "definebasiccriterion c4 duration > 5",
            "search c4",
            "definebasiccriterion c5 ",
            "definenegatedcriterion NIP IsPrimitive",
            "definenegatedcriterion c8 c7",
            "definebinarycriterion c5 c3 && c4",
            "definebinarycriterion c6 c2 || c3",
            "definebinarycriterion c7 c5 && IsPrimitive",
            "definebasiccriterion c10 prerequisites contains t0",
            "definebasiccriterion c11 subtasks contains t4",
            "search c5",
            "search c6",
            "search c7",
            "search NIP",
            "search c8",
            "search c10",
            "search c11",
            "printallcriteria",
            "store ../data2.bin",
            "quit"
    };

    private final String[] fileOpTestCommands = {
            "load ../data2.abc",
            "store test",
            "load er.",
            "load ../README.md"
    };

    private TMS tms;

    /**
     * Set up the TMS object.
     */
    @Before
    public void setUp(){
        setTms(new TMS());
    }

    /**
     * Test the TMS object.
     */
    @Test
    public void testTask() {
        getTms().testRun(taskCommands);
    }

    /**
     * Test the TMS object.
     */
    @Test
    public void testCriteria(){
        getTms().testRun(criteriaCommands);}

    /**
     * Test the TMS object.
     */
    @Test
    public void testFileOp(){
        getTms().testRun(fileOpTestCommands);
    }

    /**
     * The TMS object used to test.
     * @return the TMS object
     */
    public TMS getTms() {
        return tms;
    }

    /**
     * Set the TMS object used to test.
     * @param tms the TMS object
     */
    public void setTms(TMS tms) {
        this.tms = tms;
    }
}