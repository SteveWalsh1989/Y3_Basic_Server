import javafx.application.Platform;

import java.io.File;

/**
 * Created by Steve on 03/10/2018.
 */
public class User_Observer implements Observer_Interface{


    @Override
    public void update() {
        File folder = new File("/Users/Steve/Documents/DistributedSystemsProject/Server");

        String[] fileNames = new String[(int) folder.length()];

        if (folder.isDirectory()) {
            fileNames = folder.list();
        }

        File_Directory.printFileNames(fileNames);



        Platform.runLater(new Runnable(){               // allows update to run in separate thread
            @Override
            public void run() {
                // place the code here, that you want to execute
                Controller.clear_local_list();


                Controller.update_local_list();

            }

        });



        File_Directory.hasChanged = false;

    }
}
