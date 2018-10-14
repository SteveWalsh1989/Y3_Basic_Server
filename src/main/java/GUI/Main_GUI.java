package GUI;

import Controller.Controller;
import Local_Directory.Local_File_Directory;
import Server_Directory.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Main_GUI
 *
 * Main dashboard for application
 *
 * User Can select from Local or Server Directories
 *
 * Upload Button   : move files from local to server
 * DownLoad Button : move files from server to local
 * Play Button     : play local file
 * Clear Button    : clear list selection
 */
public class Main_GUI {

    public static HBox mainScreen;
    public static Scene main_gui;

    // Local media section
    public static VBox        local_media_box;
    public static Label       local_media_label;
    public static ListView    local_media_list;

    // server media section
    public static VBox        server_media_box;
    public static Label       server_media_label;
    public static ListView    server_media_list;

    //buttons
    public static Button download_button;
    public static Button upload_button;
    public static Button play_file_button;
    public static Button clear_button;


    public static void load_GUI(Stage primaryStage){


        mainScreen = new HBox();                                                        // create new vBox to tore options

        main_gui = new Scene(mainScreen, 800, 450);                       // Create the Scene // create new scene, add VBOX

        mainScreen.setAlignment(Pos.CENTER);                                            // center labels and buttons

        mainScreen.setSpacing(25);                                                      // add spacing between elements

        mainScreen.setPadding(new Insets(10,10,10,10));          // add padding around sections

        //---------------------------
        // Server Media Section
        //---------------------------

        server_media_box = new VBox();

        server_media_label   = new Label("Server Media");                               // local media table title

        server_media_label.setMaxHeight(35);

        server_media_list = new ListView();

        server_media_list.getItems().addAll(Server_File_Directory.fileNames);

        server_media_box.getChildren().addAll(server_media_label,server_media_list );

        //---------------------------
        // Center Section
        //---------------------------

        // Layout
        VBox center_section = new VBox();

        center_section.setSpacing(30);

        center_section.setAlignment(Pos.CENTER);

        center_section.setMinWidth(80);

        center_section.setMaxWidth(100);

        // Download Button
        download_button = new Button("Download");

        download_button.setMinWidth(80);

        // Button Action
        download_button.setOnAction(e-> {

            System.out.println("User Selected to Download: " + server_media_list.getSelectionModel().getSelectedItem());

            Controller.download();
        });


        // Upload Button
        upload_button = new Button("Upload");

        upload_button.setMinWidth(80);

        // Button Action
        upload_button.setOnAction(e-> {

            System.out.println("User Selected to Upload: " + local_media_list.getSelectionModel().getSelectedItem());

            Controller.upload();
        });

        // Play Button
        play_file_button = new Button("Play");

        play_file_button.setMinWidth(80);

        // Button Action
        play_file_button.setOnAction(e-> {

            System.out.println("User Selected to Play: " + local_media_list.getSelectionModel().getSelectedItem());

            Controller.play(); // Load Media Player

        });


        Label spacingLabel = new Label();

        // clear button
        clear_button = new Button("Clear");

        clear_button.setMinWidth(80);

        // Button Action
        clear_button.setOnAction(e -> {

          clear_selection(); // clear list views

        });

        center_section.getChildren().addAll(download_button,upload_button,play_file_button,spacingLabel, clear_button );


        //---------------------------
        // Local Media Section
        //---------------------------
        local_media_box = new VBox();

        local_media_label   = new Label("Local Media");

        local_media_label.setMaxHeight(35);

        local_media_list = new ListView();

        local_media_list.getItems().addAll(Local_File_Directory.fileNames);                   // add items from directory to list

        local_media_box.getChildren().addAll(local_media_label,local_media_list );            // add label and list to VBox

        // Set main Scene and Stage
        mainScreen.getChildren().addAll(local_media_box, center_section, server_media_box);   // add 3 sections to main hbox

        primaryStage.setTitle("R00151053 Distributed Systems Project");                       // set title of primary stage

        primaryStage.setScene(main_gui);                                                      // add homePage scene to primary stage

        primaryStage.setMaxWidth(650);
        primaryStage.setMinWidth(450);

        primaryStage.setMaxHeight(700);
        primaryStage.setMinHeight(500);

        primaryStage.show();                                                            // show primary stage

    }



    public static void bind_buttons_to_lists(){

        play_file_button.disableProperty().bind(local_media_list.getSelectionModel().selectedItemProperty().isNull());
        upload_button.disableProperty().bind(local_media_list.getSelectionModel().selectedItemProperty().isNull());
        download_button.disableProperty().bind(server_media_list.getSelectionModel().selectedItemProperty().isNull());


    }

    /**
     *clear_selection
     *
     * Clears both list views
     */
    public static void clear_selection(){

            local_media_list.getSelectionModel().select(null);
            server_media_list.getSelectionModel().select(null);


    }


}
