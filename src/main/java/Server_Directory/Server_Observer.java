package Server_Directory;

import Controller.Main_Controller;
import Interfaces.Observer_Interface;
import javafx.application.Platform;

import java.io.File;

/**
 *
 * Server interface
 * Created by Steve on 06/10/2018.
 */
public class Server_Observer implements Observer_Interface {




    @Override
    public void update() {
        java.io.File folder = new File("/Users/Steve/Documents/DistributedSystemsProject/Server");

        String[] fileNames = new String[(int) folder.length()];

        if (folder.isDirectory()) {
            fileNames = folder.list();
        }

        Server_File_Directory.printFileNames(fileNames);


        // allows update to run in separate thread
        Platform.runLater(Main_Controller::update);



        Server_File_Directory.hasChanged = false;

    }
}
