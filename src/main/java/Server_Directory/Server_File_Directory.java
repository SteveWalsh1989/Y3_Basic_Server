package Server_Directory;

import Interfaces.Observer_Interface;
import Interfaces.Subject_Interface;

import java.io.File;
import java.util.ArrayList;

/**
 * Server File directory subject class
 *
 * Stores list of observers for a directory
 */
public class Server_File_Directory implements Subject_Interface {


    private ArrayList<Observer_Interface> observers = new ArrayList<>();

    public static String[] fileNames;

    public static boolean hasChanged = false;

    public Server_File_Directory() {
    }

    /**
     * compareDirectory
     *
     * if change sets to true
     */
    public static void compareDirectory() {

        String[] newFiles = getFileNamesFromDirectory();

        for (int i = 0; i < (newFiles.length-1); i++) {

            if (fileNames[i].compareTo(newFiles[i]) != 0) {

                fileNames = newFiles;

                hasChanged = true;
            }
        }

    }



    /**
     * getFileNamesFromDirectory
     *
     * @return fileNames: list of all files in the directory
     */
    public static String[] getFileNamesFromDirectory() {

        File folder = new File("/Users/Steve/Documents/DistributedSystemsProject/Server");

        String[] fileNames = new String[(int) folder.length()];

        if (folder.isDirectory()) {
            fileNames = folder.list();
        }
        return fileNames;
    }



    public static void printFileNames(String[] files){

        System.out.println("Server Directory Files:");
        System.out.println("----------------------");

        for (int i = 0; i < (files.length); i++) {


            System.out.println(files[i]);
        }
        System.out.println("----------------------");

    }

    @Override
    public void registerObserver(Observer_Interface observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer_Interface observer) {
        observers.remove(observer);

    }

    @Override
    public void notifyObservers() {
        for (Observer_Interface ob : observers) {

            ob.update();
        }
    }
}