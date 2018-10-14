package Local_Directory;

import Interfaces.Observer_Interface;
import javafx.application.Platform;
import Controller.*;

import java.io.File;

/**
 * Observer for the local media file directory
 *
 *
 */
public class Local_Observer implements  Observer_Interface {


    @Override
    public void update() {
        java.io.File folder = new File("/Users/Steve/Documents/DistributedSystemsProject/Local");

        String[] fileNames = new String[(int) folder.length()];

        if (folder.isDirectory()) {
            fileNames = folder.list();
        }

        Local_File_Directory.printFileNames(fileNames);



        Platform.runLater(new Runnable(){               // allows update to run in separate thread
            @Override
            public void run() {
                // place the code here, that you want to execute
                Main_Controller.update();

            }

        });



        Local_File_Directory.hasChanged = false;

    }
}
