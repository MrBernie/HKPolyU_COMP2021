package hk.edu.polyu.comp.comp2021.tms.model;

import hk.edu.polyu.comp.comp2021.tms.model.CRITERION.Criterion;
import hk.edu.polyu.comp.comp2021.tms.model.CRITERION.Operand;
import hk.edu.polyu.comp.comp2021.tms.model.CRITERION.Property;
import hk.edu.polyu.comp.comp2021.tms.model.TASK.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CheckAvailability {

    private static final Pattern NAMEPATTERN = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]{0,7}$");
    private static final Pattern DESCRIPTIONPATTERN = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9-]*$");

    /**
     * Check if the name of a task is legal.
     * @param name
     * @throws Exception
     */
    protected static void checkName(String name) throws Exception{
        Matcher matcher = NAMEPATTERN.matcher(name);
        if(!matcher.matches())throw new Exception("The task name is illegal.");
    }

    /**
     * Check if the description of a task is legal.
     * @param description
     * @throws Exception
     */
    protected static void checkDescription(String description) throws Exception{
        Matcher matcher = DESCRIPTIONPATTERN.matcher(description);
        if(!matcher.matches())throw new Exception("The task description is illegal.");
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
            throw new Exception ("Duration must be a number");
        }
        if(!(duration > 0)) throw new Exception("Duration must be positive.");
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
        if(task==null) throw new Exception("This task does not exist.");
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
        if(task != null) throw new Exception("This task already exists.");
    }

    /**
     * Check if a criterion name has corresponding criterion object in the list.
     * @param storageLists
     * @param name
     * @return
     * @throws Exception
     */
    protected static Criterion checkCriterionExists(StorageLists storageLists, String name) throws Exception{
        Criterion criterion = storageLists.searchCriterionList(name);
        if(criterion==null) throw new Exception("This criterion does not exist.");
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
        if(criterion != null) throw new Exception("This criterion already exists.");
    }

    /**
     * Check if a property exists.
     * Return that property if it exists.
     * @param pro
     * @return
     * @throws Exception
     */
    protected static Property checkProperty(String pro) throws Exception{
        Property property = Property.getProperty(pro);
        if(property == null) throw new Exception ("Invalid property input.");
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
        Operand operand = Operand.getOperand(op);
        if(operand == null) throw new Exception ("Invalid operand input.");
        return operand;
    }

    protected static void checkOperandValueMatch(Operand operand, String[] value) throws Exception{
        switch (operand){
            case LESS,GREATER,GREATEROREQUAL,LESSOREQUAL,NOTEQUAL:
                if(value.length>1) throw new Exception("Too much input for value.");
                CheckAvailability.checkDuration(value[0]);
                return;
            //Todo
        }
    }

}
