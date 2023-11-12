package hk.edu.polyu.comp.comp2021.tms.model;

import java.util.Scanner;

public class TMS {

    //if we need to implement Undo and Redo, we need to create multiple storageLists.
    private static StorageLists storageLists;

    //This is the corresponding input size of each command
    //The index of this array corresponding to the number of Req
    //Ex Req 1 requires 5 input string splitted by space
    //Default input size of Req 0 is 1.
    //Req 11 has two sizes
    private static final int[] COMMAND_LENGTH = {1,
            5,//Req1
            4,//Req2
            2,//Req3
            4,//Req4
            2,//Req5
            1,//Req6
            2,//Req7
            2,//Req8
            5,//Req9
            3,//Req11 index 10
            5,//Req11
            1,//Req12
            2,//Req13
            2,//Req14
            2,//Req15
            1,//Req16
    };

    private static final Exception INVALID_COMMAND = new Exception("Invalid input command.");
    private static final Exception INVALID_PARAMETERS = new Exception("Invalid input parameters");

    public TMS(){
        storageLists = new StorageLists();
    }

    /**
     * This is the primary function that should be executed when running the TMS.
     */
    public void run(){
        boolean flag = true;
        while(flag){
            String[] inputStringArray = input(); //Get the input
            String output;

            //Get the output. Set output as the exception message if exception occurs.
            try {
                output = operation(inputStringArray);
            } catch (Exception e) {
                output = e.getMessage();
            }
            if(output.equals("quit")) flag = false;
            output(output);
        }
    }

    /**
     * Taking input from users, returning a String[] split by space.
     * @return
     */
    private String[] input(){
        Scanner s = new Scanner(System.in);
        System.out.println("Input your command here: ");
        String rawInput = s.nextLine();
        return rawInput.split(" ");
    }

    /**
     * The primary switch-case method that decides which operation should be executed.
     * The switch-case follows the order of Reqs.
     * @param inputStringArray
     * @return
     * @throws Exception
     */
    private String operation(String[] inputStringArray) throws Exception {
        switch(inputStringArray[0].toLowerCase()){

            case "createsimpletask":
                if(inputStringArray.length != COMMAND_LENGTH[1]) throw INVALID_PARAMETERS;
                return TaskOperation.createSimpleTask(storageLists,
                        inputStringArray[1],
                        inputStringArray[2],
                        inputStringArray[3],
                        inputStringArray[4].split(","));

            case "createcompositetask":
                if(inputStringArray.length != COMMAND_LENGTH[2]) throw INVALID_PARAMETERS;
                return TaskOperation.createCompositeTask(storageLists,
                        inputStringArray[1],
                        inputStringArray[2],
                        inputStringArray[3].split(","));

            case "deletetask":
                if(inputStringArray.length != COMMAND_LENGTH[3]) throw INVALID_PARAMETERS;
                return TaskOperation.deleteTask(storageLists,
                        inputStringArray[1]);

            case "changetask":
                if(inputStringArray.length != COMMAND_LENGTH[4]) throw INVALID_PARAMETERS;
                return TaskOperation.setProperty(storageLists,
                        inputStringArray[1],
                        inputStringArray[2],
                        inputStringArray[3].split(","));

            case "printtask":
                if(inputStringArray.length != COMMAND_LENGTH[5]) throw INVALID_PARAMETERS;
                return TaskOperation.printTask(storageLists,inputStringArray[1]);

            case "printalltasks":
                if(inputStringArray.length != COMMAND_LENGTH[6]) throw INVALID_PARAMETERS;
                return TaskOperation.printAllTasks(storageLists);

            case "reportduration":
                if(inputStringArray.length != COMMAND_LENGTH[7]) throw INVALID_PARAMETERS;
                return TaskOperation.reportDuration(storageLists,
                        inputStringArray[1]);

            case "reportearliestfinishtime":
                if(inputStringArray.length != COMMAND_LENGTH[8]) throw INVALID_PARAMETERS;
                return TaskOperation.reportEarliestFinishTime(storageLists,
                        inputStringArray[1]);

            case "definebasiccriterion":
                if(inputStringArray.length != COMMAND_LENGTH[9]) throw INVALID_PARAMETERS;
                return CriterionOperation.defineBasicCriterion(storageLists,
                        inputStringArray[1],
                        inputStringArray[2],
                        inputStringArray[3],
                        inputStringArray[4].split(","));

            case "definenegatedcriterion":
                if(inputStringArray.length != COMMAND_LENGTH[10]) throw INVALID_PARAMETERS;
                return CriterionOperation.defineNegatedCriterion(storageLists,
                        inputStringArray[1],
                        inputStringArray[2]);

            case "definebinarycriterion":
                if(inputStringArray.length != COMMAND_LENGTH[11]) throw INVALID_PARAMETERS;
                return CriterionOperation.defineBinaryCriterion(storageLists,
                        inputStringArray[1],
                        inputStringArray[2],
                        inputStringArray[3],
                        inputStringArray[4]);

            case "printallcriteria":
                if(inputStringArray.length != COMMAND_LENGTH[12]) throw INVALID_PARAMETERS;
                return CriterionOperation.printAllCriteria(storageLists);

            case "search":
                if(inputStringArray.length != COMMAND_LENGTH[13]) throw INVALID_PARAMETERS;
                return CriterionOperation.search(storageLists,
                        inputStringArray[1]);

            case "store":
                if(inputStringArray.length != COMMAND_LENGTH[14]) throw INVALID_PARAMETERS;
                return FileOperation.writeFile(storageLists,
                        inputStringArray[1]);

            case "load":
                if(inputStringArray.length != COMMAND_LENGTH[15]) throw INVALID_PARAMETERS;
                return FileOperation.readFile(storageLists,
                        inputStringArray[1]);

            case "quit":
                return "quit";
            default:
                throw INVALID_COMMAND;
        }
    }

    /**
     * Simply prints out the output.
     * @param output
     */
    private void output(String output){
        System.out.println(output + "\n");
        //Todo may need further implementation.
    }

}
