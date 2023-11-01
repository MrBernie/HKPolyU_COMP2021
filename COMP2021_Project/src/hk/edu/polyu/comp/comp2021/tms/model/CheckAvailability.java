package hk.edu.polyu.comp.comp2021.tms.model;

import hk.edu.polyu.comp.comp2021.tms.model.CRITERION.*;
import hk.edu.polyu.comp.comp2021.tms.model.TASK.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CheckAvailability {

    /*Check the availability of task operation.*/
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]{0,7}$");
    private static final Pattern DESCRIPTION_PATTERN = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9-]*$");

    private static final Exception ILLEGAL_NAME = new Exception("The task name is illegal.");
    private static final Exception ILLEGAL_DESCRIPTION = new Exception("The task description is illegal.");
    private static final Exception ILLEGAL_DURATION1 = new Exception ("Duration must be a number.");
    private static final Exception ILLEGAL_DURATION2 = new Exception("Duration must be positive.");
    private static final Exception TASK_NOT_EXIST = new Exception("The required task does not exist.");
    private static final Exception TASK_ALREADY_EXIST = new Exception("This task already exists.");

    /**
     * Check if the name of a task is legal.
     * @param name
     * @throws Exception
     */
    protected static void checkName(String name) throws Exception{
        Matcher matcher = NAME_PATTERN.matcher(name);
        if(!matcher.matches())throw ILLEGAL_NAME;
    }

    /**
     * Check if the description of a task is legal.
     * @param description
     * @throws Exception
     */
    protected static void checkDescription(String description) throws Exception{
        Matcher matcher = DESCRIPTION_PATTERN.matcher(description);
        if(!matcher.matches())throw ILLEGAL_DESCRIPTION;
    }

    /**
     * Check the availability of the duration
     * @param dur
     * @throws Exception
     */
    protected static Double checkDuration(String dur) throws Exception{
        Double duration;
        try{
            duration = Double.parseDouble(dur);
        }catch(NumberFormatException e){
            throw ILLEGAL_DURATION1;
        }
        if(!(duration > 0)) throw ILLEGAL_DURATION2;
        return duration;
    }

    /**
     * Check if a task name has corresponding task object in the list.
     * Return the task if it exists in the list.
     * @param storageLists
     * @param name
     * @return
     * @throws Exception
     */
    protected static Task checkTaskExists(StorageLists storageLists, String name) throws Exception{
        Task task = storageLists.searchTaskList(name);
        if(task==null) throw TASK_NOT_EXIST;
        return task;
    }

    /**
     * Check if a task is already existing in the list.
     * @param storageLists
     * @param name
     * @throws Exception
     */
    protected static void checkTaskAlreadyExists(StorageLists storageLists, String name) throws Exception{
        Task task = storageLists.searchTaskList(name);
        if(task != null) throw TASK_ALREADY_EXIST;
    }

    /******************************************************/
    /*Check the availability of criterion operation.*/

    private static final Exception CRITERION_NOT_EXIST = new Exception("The required criterion does not exist.");
    private static final Exception CRITERION_ALREADY_EXIST = new Exception("This criterion already exists.");

    private static final Exception PROPERTY_OPERAND_EXCEPTION = new Exception("Property and Operand do not match.");
    private static final Exception ILLEGAL_VALUE = new Exception("Too much input for value.");
    private static final Exception DOUBLE_QUOTE = new Exception("The value must be double-quoted.");
    private static final Exception ILLEGAL_OPERAND_INPUT = new Exception ("Invalid property input.");
    private static final Exception ILLEGAL_LOGICAL_OPERAND = new Exception ("Invalid logical operand.");

    /**
     * Check if a criterion name has corresponding criterion object in the list.
     * @param storageLists
     * @param name
     * @return
     * @throws Exception
     */
    protected static Criterion checkCriterionExists(StorageLists storageLists, String name) throws Exception{
        Criterion criterion = storageLists.searchCriterionList(name);
        if(criterion==null) throw CRITERION_NOT_EXIST;
        return criterion;
    }

    /**
     * Check if a criterion is already existing in the list.
     * @param storageLists
     * @param name
     * @throws Exception
     */
    protected static void checkCriterionAlreadyExists(StorageLists storageLists, String name) throws Exception{
        Criterion criterion = storageLists.searchCriterionList(name);
        if(criterion != null) throw CRITERION_ALREADY_EXIST;
    }

    /**
     * Check if a property exists.
     * Return that property if it exists.
     * @param pro
     * @return
     * @throws Exception
     */
    protected static Property checkProperty(String pro) throws Exception{
        Property property = Property.getProperty(pro.toLowerCase());
        if(property == null) throw ILLEGAL_OPERAND_INPUT;
        return property;
    }

    /**
     * Check if an operand exists.
     * Return that operand if it exists.
     * @param op
     * @return
     * @throws Exception
     */
    protected static Operand checkOperand(String op) throws Exception{
        Operand operand = Operand.getOperand(op.toLowerCase());
        if(operand == null) throw ILLEGAL_OPERAND_INPUT;
        return operand;
    }

    protected static LogicOp checkLogicOp(String lOp) throws Exception{
        LogicOp logicOp = LogicOp.getLogicOp(lOp);
        if(logicOp == null) throw ILLEGAL_LOGICAL_OPERAND;
        return logicOp;
    }

    /**
     * Check if the input of basic criterion property, operand, and value are matched.
     * @param property
     * @param operand
     * @param value
     * @throws Exception
     */
    protected static void checkPropertyOperandValueMatch(Property property,
                                                         Operand operand, String[] value) throws Exception{
        switch (property){
            case NAME,DESCRIPTION:
                if(value.length>1) throw ILLEGAL_VALUE;
                if(value[0].length()<2||!value[0].startsWith("\"")||!value[0].endsWith("\""))
                    throw DOUBLE_QUOTE;
            case PREREQUISITE,SUBTASKS:
                if(!operand.equals(Operand.CONTAINS)) throw PROPERTY_OPERAND_EXCEPTION;
                return;

            case DURATION:
                if(operand.equals(Operand.CONTAINS)) throw PROPERTY_OPERAND_EXCEPTION;
                CheckAvailability.checkDuration(value[0]);
                if(value.length>1) throw ILLEGAL_VALUE;
                return;
        }
    }

}
