package hk.edu.polyu.comp.comp2021.tms.model;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the Task Management System.
 * It is the main class of the program.
 */
public class TMS {

    //if we need to implement Undo and Redo, we need to create multiple storageLists.
    private StorageLists storageLists;
    private ArrayList<StorageLists> redoList;
    private ArrayList<StorageLists> undoList;

//    This is the corresponding input size of each command
//    The index of this array corresponding to the number of Req
//    Ex Req 1 requires 5 input string splitted by space
//    Default input size of Req 0 is 1.
//    Req 11 has two sizes
//    private static final int[] COMMAND_LENGTH = {1,
//            5,//Req1
//            4,//Req2
//            2,//Req3
//            4,//Req4
//            2,//Req5
//            1,//Req6
//            2,//Req7
//            2,//Req8
//            5,//Req9
//            3,//Req11 index = 10
//            5,//Req11
//            1,//Req12
//            2,//Req13
//            2,//Req14
//            2,//Req15
//            1,//Req16
//    };

    private static final Exception INVALID_COMMAND = new Exception("Invalid input command.");
    private static final Exception INVALID_PARAMETERS = new Exception("Invalid input parameters");

    /**
     * Constructor of TMS.
     */
    public TMS(){
        storageLists = new StorageLists();
    }
    private boolean flag;

    private Scanner scanner;

    /**
     * This is the primary function that should be executed when running the TMS.
     */
    public void run(){
        flag = true;
        while(flag){
            String[] inputStringArray = input(); //Get the input
            String output;
            //Get the output. Set output as the exception message if exception occurs.
            try {
                output = operation(inputStringArray);
            } catch (Exception e) {
                output = e.getMessage();
            }
            output(output);
        }
        scanner.close();
    }

    /**
     * This is the primary function that should be executed when testing the TMS.
     * @param commandLines The command lines to be executed.
     */
    public void testRun(String[] commandLines){
        flag = true;
        for(String command : commandLines){
            String[] inputStringArray = command.split(" "); //Get the input
            String output;
            //Get the output. Set output as the exception message if exception occurs.
            try {
                output = operation(inputStringArray);
            } catch (Exception e) {
                output = e.getMessage();
            }
            output(output);
        }
    }

//    /**
//     * This is the primary function that should be executed when testing the TMS.
//     * @param commandLine The command line to be executed.
//     * @return The output String.
//     */
//    public String testCommand(String commandLine){
//        flag = true;
//        String[] inputStringArray = commandLine.split(" "); //Get the input
//        String output;
//        //Get the output. Set output as the exception message if exception occurs.
//        try {
//            output = operation(inputStringArray);
//        } catch (Exception e) {
//            output = e.getMessage();
//        }
//        return output;
//    }

    /**
     * Taking input from users, returning a String[] split by space.
     * @return String[]
     */
    private String[] input(){
        scanner = new Scanner(System.in);
        System.out.println("Input your command here: ");
        String rawInput = scanner.nextLine();
        return rawInput.split(" ");
    }

    /**
     * The primary switch-case method that decides which operation should be executed.
     * The switch-case follows the order of Reqs.
     * @param inputStringArray The input String[] split by space.
     * @return The output String.
     * @throws Exception If the input command is invalid or the input parameters are invalid.
     */
    protected String operation(String[] inputStringArray) throws Exception {
        switch (inputStringArray[0].toLowerCase()) {
            case "createsimpletask" -> {
                if (inputStringArray.length != 5) throw INVALID_PARAMETERS;
                return TaskOperation.createSimpleTask(storageLists,
                        inputStringArray[1],
                        inputStringArray[2],
                        inputStringArray[3],
                        inputStringArray[4].split(","));
            }
            case "createcompositetask" -> {
                if (inputStringArray.length != 4) throw INVALID_PARAMETERS;
                return TaskOperation.createCompositeTask(storageLists,
                        inputStringArray[1],
                        inputStringArray[2],
                        inputStringArray[3].split(","));
            }
            case "deletetask" -> {
                if (inputStringArray.length != 2) throw INVALID_PARAMETERS;
                return TaskOperation.deleteTask(storageLists,
                        inputStringArray[1]);
            }
            case "changetask" -> {
                if (inputStringArray.length != 4) throw INVALID_PARAMETERS;
                return TaskOperation.setProperty(storageLists,
                        inputStringArray[1],
                        inputStringArray[2],
                        inputStringArray[3].split(","));
            }
            case "printtask" -> {
                if (inputStringArray.length != 2) throw INVALID_PARAMETERS;
                return TaskOperation.printTask(storageLists, inputStringArray[1]);
            }
            case "printalltasks" -> {
                if (inputStringArray.length != 1) throw INVALID_PARAMETERS;
                return TaskOperation.printAllTasks(storageLists);
            }
            case "reportduration" -> {
                if (inputStringArray.length != 2) throw INVALID_PARAMETERS;
                return TaskOperation.reportDuration(storageLists,
                        inputStringArray[1]);
            }
            case "reportearliestfinishtime" -> {
                if (inputStringArray.length != 2) throw INVALID_PARAMETERS;
                return TaskOperation.reportEarliestFinishTime(storageLists,
                        inputStringArray[1]);
            }
            case "definebasiccriterion" -> {
                if (inputStringArray.length != 5) throw INVALID_PARAMETERS;
                return CriterionOperation.defineBasicCriterion(storageLists,
                        inputStringArray[1],
                        inputStringArray[2],
                        inputStringArray[3],
                        inputStringArray[4].split(","));
            }
            case "definenegatedcriterion" -> {
                if (inputStringArray.length != 3) throw INVALID_PARAMETERS;
                return CriterionOperation.defineNegatedCriterion(storageLists,
                        inputStringArray[1],
                        inputStringArray[2]);
            }
            case "definebinarycriterion" -> {
                if (inputStringArray.length != 5) throw INVALID_PARAMETERS;
                return CriterionOperation.defineBinaryCriterion(storageLists,
                        inputStringArray[1],
                        inputStringArray[2],
                        inputStringArray[3],
                        inputStringArray[4]);
            }
            case "printallcriteria" -> {
                if (inputStringArray.length != 1) throw INVALID_PARAMETERS;
                return CriterionOperation.printAllCriteria(storageLists);
            }
            case "search" -> {
                if (inputStringArray.length != 2) throw INVALID_PARAMETERS;
                return CriterionOperation.search(storageLists,
                        inputStringArray[1]);
            }
            case "store" -> {
                if (inputStringArray.length != 2) throw INVALID_PARAMETERS;
                return FileOperation.writeFile(storageLists,
                        inputStringArray[1]);
            }
            case "load" -> {
                if (inputStringArray.length != 2) throw INVALID_PARAMETERS;
                storageLists = FileOperation.readFile(storageLists,
                        inputStringArray[1]);
                return "File has been loaded";
            }
            case "quit" -> {
                stop();
                return "quit";
            }
            default -> throw INVALID_COMMAND;
        }
    }

    /**
     * Simply prints out the output.
     * @param output the output to be printed.
     */
    private void output(String output){
        System.out.println(output + "\n");
    }

    /**
     * Stops the TMS.
     */
    protected void stop() {flag = false;}

}
