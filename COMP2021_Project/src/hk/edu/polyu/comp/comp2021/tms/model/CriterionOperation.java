package hk.edu.polyu.comp.comp2021.tms.model;

import hk.edu.polyu.comp.comp2021.tms.model.CRITERION.*;

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
        CheckAvailability.checkCriterionAlreadyExists(storageList,name);
        Property pro = CheckAvailability.checkProperty(property);
        Operand op = CheckAvailability.checkOperand(operand);
        CheckAvailability.checkPropertyOperandValueMatch(pro,op,value);
        storageList.defineBasicCriterion(name,pro,op,value);
        return "Basic criterion \""+name+"\" has been defined successfully.";
    }

}
