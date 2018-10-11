import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{




     StartObserver serverObserver = new StartObserver(); // initiate new observer for server class

     serverObserver.start();                             // start server observer



        GUI.load_GUI(primaryStage);  // starts with loading the GUI

        primaryStage.show();         // show the primary stage



    }


    public static void main(String[] args) {
        launch(args);
    }
}
