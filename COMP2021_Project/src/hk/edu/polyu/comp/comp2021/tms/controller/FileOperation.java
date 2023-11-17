package hk.edu.polyu.comp.comp2021.tms.controller;

import hk.edu.polyu.comp.comp2021.tms.model.StorageLists;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the file operation.
 * The file operation is used to read and write the data to the file.
 * It is consists of two static methods: writeFile and readFile.
 */
public class FileOperation {
    private static StorageLists storageLists;
    private static final List<StorageLists> storageListsUndo = new ArrayList<>();
    private static final List<StorageLists> storageListsRedo = new ArrayList<>();

    public static StorageLists getStorageLists() {
        return storageLists;
    }
    public static void setStorageLists(StorageLists storageLists) {
        FileOperation.storageLists = storageLists;
    }

    static {
        storageLists = new StorageLists();
    }

    public static void transaction() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(storageLists);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bis);
            storageListsUndo.add((StorageLists) in.readObject());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void undo() {
        if (!storageListsUndo.isEmpty()) {
            storageListsRedo.add(storageLists);
            storageLists = storageListsUndo.get(storageListsUndo.size() - 1);
            storageListsUndo.remove(storageLists);
        }
    }

    public static void redo() {
        if (!storageListsRedo.isEmpty()) {
            storageListsUndo.add(storageLists);
            storageLists = storageListsRedo.get(storageListsRedo.size() - 1);
            storageListsRedo.remove(storageLists);
        }
    }

    /**
     * Write the data to the file.
     * @param storageLists The data to be stored.
     * @param filePath The file path.
     * @return The message of the operation.
     * @throws Exception The exception to the operation.
     */
    public static String writeFile(StorageLists storageLists, String filePath) throws Exception{
        if(filePath == null || filePath.isEmpty()){
            return "File is null";
        }
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream ObjOut = new ObjectOutputStream(fileOut)) {
            ObjOut.writeObject(storageLists);
            return "The data has been stored to the file.";
        } catch (FileNotFoundException e) {
            throw new Exception("The file path is illegal.");
        } catch (IOException e) {
            throw new Exception("IO exception.");
        }
    }

    /**
     * Read the data from the file.
     *
     * @param filePath The file path.
     * @return The message of the operation.
     * @throws Exception The exception to the operation.
     */
    public static StorageLists readFile(String filePath) throws Exception {
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