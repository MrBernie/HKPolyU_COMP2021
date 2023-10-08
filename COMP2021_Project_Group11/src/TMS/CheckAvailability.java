package TMS;
import TMS.TASK.*;

public class CheckAvailability {

    public static void checkName(String Name) throws Exception{
        //Todo
    }

    public static void checkDescription(String Description) throws Exception{
        //Todo
    }

    public static void checkDuration(double duration) throws Exception{
        if(!(duration > 0)) throw new Exception("Duration must be positive.");
    }

}
