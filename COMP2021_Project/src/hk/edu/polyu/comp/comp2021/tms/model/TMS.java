package hk.edu.polyu.comp.comp2021.tms.model;

public class TMS {

    //if we need to implement Undo and Redo, we need to create multiple storageLists -> Delete static in this case.
    private static StorageLists storageLists;

    public TMS(){
        storageLists = new StorageLists();
    }

    public void run(){

    }

    private String messageShownBefore(){
        return "";
    }

}
