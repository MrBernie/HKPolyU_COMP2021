package hk.edu.polyu.comp.comp2021.tms.model;

import hk.edu.polyu.comp.comp2021.tms.model.CRITERION.*;

class CriterionOperation {

    /**
     * Req 9
     * @param name
     * @param pro
     * @param op
     * @param value
     * @return
     */
    static String defineBasicCriterion(StorageLists storageList,String name,
                                       String pro, String op, String[] value) throws Exception{

        CheckAvailability.checkCriterionAlreadyExists(storageList,name);
        Property property = CheckAvailability.checkProperty(pro);
        Operand operand = CheckAvailability.checkOperand(op);

        //Todo
        return "";
    }

}
