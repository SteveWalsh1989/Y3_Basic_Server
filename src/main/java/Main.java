import GUI.Main_GUI;
import Local_Directory.Start_Local_Observer;
import Server_Directory.Start_Server_Observer;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{


        //----------------
        // Local
        //----------------

       Start_Local_Observer localObserver = new Start_Local_Observer(); // initiate new observer for local class

       localObserver.start();                                           // start local observer

        //----------------
        // server
        //----------------
       Start_Server_Observer serverObserver = new Start_Server_Observer(); // initiate new observer for server class

       serverObserver.start();                                             // start server observer







        //----------------
        // GUI
        //----------------
        Main_GUI.load_GUI(primaryStage);  // starts with loading the Main_GUI.Main_GUI

        Main_GUI.bind_buttons_to_lists(); // binds play and download buttons to list views

        primaryStage.show();              // show the primary stage



    }


    public static void main(String[] args) {
        launch(args);
    }
}
