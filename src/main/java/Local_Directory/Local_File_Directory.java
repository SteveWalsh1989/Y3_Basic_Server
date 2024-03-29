package Local_Directory;

import Interfaces.Observer_Interface;
import Interfaces.Subject_Interface;

import java.io.File;
import java.util.ArrayList;

/**
 * Local File directory subject class
 *
 * Stores list of observers for a directory
 */
public class Local_File_Directory implements Subject_Interface {


    private final ArrayList<Observer_Interface> observers = new ArrayList<>();

    public static String[] fileNames;

    public static boolean hasChanged = false;

    public Local_File_Directory() {

    }

    /**
     * compareDirectory
     * <p>
     * if change sets to true
     */
    public static void compareDirectory() {

        String[] newFiles = getFileNamesFromDirectory();

        for (int i = 0; i < (newFiles.length - 1); i++) {

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

        java.io.File folder = new File("/Users/Steve/Documents/DistributedSystemsProject/Local");

        String[] fileNames = new String[(int) folder.length()];

        if (folder.isDirectory()) {
            fileNames = folder.list();
        }
        return fileNames;
    }


    public static void printFileNames(String[] files) {

        System.out.println("Local Directory Files:");
        System.out.println("----------------------");


        for (String file : files) {

            System.out.println(file);
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
