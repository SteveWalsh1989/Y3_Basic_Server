import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Created by Steve on 07/10/2018.
 */
public class GUI {

    public static HBox mainScreen;
    public static Scene main_gui;

    // Local media section
    public static VBox        local_media_box;
    public static Label       local_media_label;
    public static ListView    local_media_list;
    public static TableView   local_media_table;

    // server media section
    public static VBox        server_media_box;
    public static Label       server_media_label;
    public static ListView    server_media_list;

    public static void load_GUI(Stage primaryStage){


        mainScreen = new HBox();                                                        // create new vBox to tore options

        main_gui = new Scene(mainScreen, 800, 450);                       // Create the Scene // create new scene, add VBOX

        mainScreen.setAlignment(Pos.CENTER);                                            // center labels and buttons

        mainScreen.setSpacing(25);                                                      // add spacing between elements

        mainScreen.setPadding(new Insets(10,10,10,10));          // add padding around sections

        //---------------------------
        // Local Media Section
        //---------------------------

        local_media_box = new VBox();

        local_media_label   = new Label("Local Media");                               // local media table title

        local_media_label.setMaxHeight(35);

        local_media_table = new TableView();

        local_media_list = new ListView();

        local_media_list.getItems().addAll(File_Directory.fileNames);

        local_media_box.getChildren().addAll(local_media_label,local_media_list );

        //---------------------------
        // Center Section
        //---------------------------

        VBox center_section = new VBox();

        center_section.setSpacing(30);

        center_section.setAlignment(Pos.CENTER);

        center_section.setMinWidth(80);

        center_section.setMaxWidth(100);

        Button download_file = new Button("Download");

        download_file.setMinWidth(80);



        Button play_file = new Button("Play");

        play_file.setMinWidth(80);

        center_section.getChildren().addAll(download_file,play_file);



        //---------------------------
        // Server Media Section
        //---------------------------
        server_media_box = new VBox();

        server_media_label   = new Label("Server Media");                               // local media table title

        server_media_label.setMaxHeight(35);

        server_media_list = new ListView();

        server_media_box.getChildren().addAll(server_media_label,server_media_list );


        mainScreen.getChildren().addAll(local_media_box, center_section, server_media_box);   // add 3 sections to main hbox


        primaryStage.setTitle("R00151053 Distributed Systems Project");                 // set title of primary stage

        primaryStage.setScene(main_gui);                                                // add homePage scene to primary stage

        primaryStage.setMaxWidth(650);
        primaryStage.setMinWidth(450);

        primaryStage.setMaxHeight(700);
        primaryStage.setMinHeight(500);

        primaryStage.show();                                                            // show primary stage

    }
}
