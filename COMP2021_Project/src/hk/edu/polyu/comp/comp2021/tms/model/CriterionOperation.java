package hk.edu.polyu.comp.comp2021.tms.model;

import hk.edu.polyu.comp.comp2021.tms.model.CRITERION.*;
import hk.edu.polyu.comp.comp2021.tms.model.TASK.Task;

import java.util.ArrayList;

class CriterionOperation {

    /**
     * Req 9
     * @param name
     * @param property
     * @param operand
     * @param value
     * @return
     */
    static String defineBasicCriterion(StorageLists storageList,String name,
                                       String property, String operand, String[] value) throws Exception{
        CheckAvailability.checkName(name);
        CheckAvailability.checkCriterionAlreadyExists(storageList,name);
        Property pro = CheckAvailability.checkProperty(property);
        Operand op = CheckAvailability.checkOperand(operand);
        CheckAvailability.checkPropertyOperandValueMatch(pro,op,value);
        if(pro == Property.NAME || pro == Property.DESCRIPTION) removeDoubleQuote(value);
        storageList.defineBasicCriterion(name,pro,op,value);
        return "Basic criterion \""+name+"\" has been defined successfully.";
    }

    private static void removeDoubleQuote(String[] input){
        input[0] = input[0].replace("\"","");
    }

    /**
     * Req 11_1
     * @param storageLists
     * @param name
     * @param nameOfCriterion
     * @return
     * @throws Exception
     */
    static String defineNegatedCriterion(StorageLists storageLists, String name,
                                         String nameOfCriterion) throws Exception{
        CheckAvailability.checkName(name);
        CheckAvailability.checkCriterionAlreadyExists(storageLists,name);
        Criterion criterion = CheckAvailability.checkCriterionExists(storageLists,nameOfCriterion);
        storageLists.defineNegatedCriterion(name,criterion);
        return "Negated criterion \"" + name + "\" has been defined successfully.";
    }

    /**
     * Req 11_2
     * @param storageLists
     * @param name
     * @param nameOfCriterion1
     * @param lOp
     * @param nameOfCriterion2
     * @return
     * @throws Exception
     */
    static String defineBinaryCriterion(StorageLists storageLists, String name, String nameOfCriterion1,
                                        String lOp, String nameOfCriterion2) throws Exception{
        CheckAvailability.checkName(name);
        CheckAvailability.checkCriterionAlreadyExists(storageLists, name);
        Criterion criterion1 = CheckAvailability.checkCriterionExists(storageLists, nameOfCriterion1);
        Criterion criterion2 = CheckAvailability.checkCriterionExists(storageLists, nameOfCriterion2);
        LogicOp logicOp = CheckAvailability.checkLogicOp(lOp);
        storageLists.defineBinaryCriterion(name, criterion1, logicOp, criterion2);
        return "Binary criterion \"" + name + "\" has been defined successfully.";
    }

    /**
     * Req 12
     * @param storageLists
     * @return
     */

    static String printAllCriteria(StorageLists storageLists){
        StringBuilder strB = new StringBuilder("\nStart printing all criteria...\n");
        if(storageLists.getCriterionList().isEmpty()) return "There is no criterion currently...";
        strB.append(storageLists.criterionListString());
        return strB.toString();
    }

    /**
     * Req 13
     * @param storageLists
     * @param nameOfCriterion
     * @return
     * @throws Exception
     */

    static String search(StorageLists storageLists, String nameOfCriterion) throws Exception{
        Criterion criterion = CheckAvailability.checkCriterionExists(storageLists,nameOfCriterion);
        ArrayList<Task> result = storageLists.search(criterion);
        if(result.isEmpty()) return "Cannot find the corresponding tasks.";
        StringBuilder strB = new StringBuilder();
        strB.append("These tasks meets the criterion "+nameOfCriterion+ " : ");
        for(Task t : result) strB.append(t.getName()+",");
        if(strB.charAt(strB.length()-1) == ',') strB.delete(strB.length()-1,strB.length());
        return strB.toString();
    }

}
