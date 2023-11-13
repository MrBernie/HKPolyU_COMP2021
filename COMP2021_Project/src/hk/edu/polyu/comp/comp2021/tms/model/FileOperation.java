package hk.edu.polyu.comp.comp2021.tms.model;

import java.io.*;
import java.util.*;

public class FileOperation {

    static String writeFile(StorageLists storageLists, String filePath) throws Exception{
        FileOutputStream fileOut = null;
        ObjectOutputStream ObjOut = null;
        try {
            fileOut = new FileOutputStream(filePath);
            ObjOut = new ObjectOutputStream(fileOut);
            ObjOut.writeObject(storageLists);
        } catch(FileNotFoundException e){
            throw new Exception("The file path is illegal.");
        } catch(IOException e){
            //throw e;
            throw new Exception("IO exception.");
        }finally{
            if(ObjOut != null) ObjOut.close();
            if(fileOut != null) fileOut.close();
        }
        return "The data has been stored to the file.";
    }

    static StorageLists readFile(StorageLists storageLists, String filePath) throws Exception {

        FileInputStream fileIn = null;
        ObjectInputStream ObjIn = null;
        try {
            fileIn = new FileInputStream(filePath);
            ObjIn = new ObjectInputStream(fileIn);
            storageLists = (StorageLists) ObjIn.readObject();
        } catch (FileNotFoundException e){
            throw new Exception("The file cannot be found.");
        } catch(IOException e) {
            //throw e;
            throw new Exception("IO exception.");
        } catch(ClassNotFoundException e){
            throw new Exception("The file content is unreadable.");
        }finally{
            if(ObjIn != null) ObjIn.close();
            if(fileIn != null) fileIn.close();
        }
        return storageLists;
    }

}
