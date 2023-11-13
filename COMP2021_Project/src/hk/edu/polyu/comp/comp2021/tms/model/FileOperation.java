package hk.edu.polyu.comp.comp2021.tms.model;

import java.io.*;
import java.util.*;

public class FileOperation {

    /**
     * Write the data to the file.
     * @param storageLists The data to be stored.
     * @param filePath The file path.
     * @return The message of the operation.
     * @throws Exception The exception of the operation.
     */
    static String writeFile(StorageLists storageLists, String filePath) throws Exception{
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream ObjOut = new ObjectOutputStream(fileOut)) {
            ObjOut.writeObject(storageLists);
        } catch (FileNotFoundException e) {
            throw new Exception("The file path is illegal.");
        } catch (IOException e) {
            //throw e;
            throw new Exception("IO exception.");
        }
        return "The data has been stored to the file.";
    }

    /**
     * Read the data from the file.
     * @param storageLists The data to be stored.
     * @param filePath The file path.
     * @return The message of the operation.
     * @throws Exception The exception of the operation.
     */
    static StorageLists readFile(StorageLists storageLists, String filePath) throws Exception {

        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream ObjIn = new ObjectInputStream(fileIn)) {
            storageLists = (StorageLists) ObjIn.readObject();
        } catch (FileNotFoundException e) {
            throw new Exception("The file cannot be found.");
        } catch (IOException e) {
            //throw e;
            throw new Exception("IO exception.");
        } catch (ClassNotFoundException e) {
            throw new Exception("The file content is unreadable.");
        }
        return storageLists;
    }

}
