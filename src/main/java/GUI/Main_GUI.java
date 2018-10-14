package GUI;

import Controller.Main_Controller;
import Local_Directory.Local_File_Directory;
import Server_Directory.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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

    public static ListView    local_media_list;

    public static ListView    server_media_list;

    //buttons
    private static Button download_button;
    private static Button upload_button;
    private static Button play_file_button;


    public static void load_GUI(Stage primaryStage){


        HBox mainScreen = new HBox();

        Scene main_gui = new Scene(mainScreen, 650, 400);

        mainScreen.setStyle("-fx-background-color: #E3F1F7");

        mainScreen.setAlignment(Pos.CENTER);                                            // center labels and buttons

        mainScreen.setSpacing(25);                                                      // add spacing between elements

        mainScreen.setPadding(new Insets(10,10,10,10));          // add padding around sections

        //---------------------------
        // Server Media Section
        //---------------------------

        VBox server_media_box = new VBox();

        server_media_box.setAlignment(Pos.CENTER);

        server_media_box.setSpacing(15);

        Text server_media_text = new Text("Server Media");

        server_media_text.setFill(Color.valueOf("#3C5964"));

        server_media_text.setFont(Font.font ("Verdana", 20));

        server_media_list = new ListView();

        server_media_list.setStyle("-fx-background-color: #CCE9F4");
        server_media_list.setStyle("-fx-text-fill: #698A96");


        //noinspection unchecked
        server_media_list.getItems().addAll(Server_File_Directory.fileNames);

        server_media_box.getChildren().addAll(server_media_text,server_media_list );

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

        download_button.setStyle("-fx-background-color: #E3F4FB");
        download_button.setStyle("-fx-text-fill: #3C5964");


        download_button.setMinWidth(80);

        // Button Action
        download_button.setOnAction(e-> {

            System.out.println("\n-- User Selected to Download: " + server_media_list.getSelectionModel().getSelectedItem());

            Main_Controller.download();
        });


        // Upload Button
        upload_button = new Button("Upload");

        upload_button.setStyle("-fx-background-color: #E3F4FB");
        upload_button.setStyle("-fx-text-fill: #3C5964");



        upload_button.setMinWidth(80);

        // Button Action
        upload_button.setOnAction(e-> {

            System.out.println("\n-- User Selected to Upload: " + local_media_list.getSelectionModel().getSelectedItem());

            Main_Controller.upload();
        });

        // Play Button
        play_file_button = new Button("Play");

        play_file_button.setMinWidth(80);

        play_file_button.setStyle("-fx-background-color: #E3F4FB");
        play_file_button.setStyle("-fx-text-fill: #3C5964");



        // Button Action
        play_file_button.setOnAction(e-> {

            System.out.println("\n-- User Selected to Play: " + local_media_list.getSelectionModel().getSelectedItem());

            Main_Controller.play(); // Load Media Player

        });


        Label spacingLabel = new Label();

        // clear button
        Button clear_button = new Button("Clear");

        clear_button.setStyle("-fx-background-color: #E3F4FB");
        clear_button.setStyle("-fx-text-fill: #3C5964");

        clear_button.setMinWidth(80);

        // Button Action
        clear_button.setOnAction(e -> {

          clear_selection(); // clear list views

        });

        center_section.getChildren().addAll(download_button,upload_button,play_file_button,spacingLabel, clear_button);


        //---------------------------
        // Local Media Section
        //---------------------------
        VBox local_media_box = new VBox();

        local_media_box.setAlignment(Pos.CENTER);

        local_media_box.setSpacing(15);

        Text local_media_text = new Text("Local Media");

        local_media_text.setFill(Color.valueOf("#3C5964"));

        local_media_text.setFont(Font.font ("Verdana", 20));

        local_media_list = new ListView();

        //noinspection unchecked
        local_media_list.getItems().addAll(Local_File_Directory.fileNames);                   // add items from directory to list

        local_media_box.getChildren().addAll(local_media_text,local_media_list );            // add label and list to VBox

        // Set main Scene and Stage
        mainScreen.getChildren().addAll(local_media_box, center_section, server_media_box);   // add 3 sections to main hbox


        primaryStage.setTitle("R00151053 Distributed Systems Project");                       // set title of primary stage

        primaryStage.setScene(main_gui);                                                      // add homePage scene to primary stage

        primaryStage.setMaxWidth(650);
        primaryStage.setMinWidth(650);

        primaryStage.setMaxHeight(450);
        primaryStage.setMinHeight(450);

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
    private static void clear_selection(){

        //noinspection unchecked
        local_media_list.getSelectionModel().select(null);
        //noinspection unchecked
        server_media_list.getSelectionModel().select(null);


    }


}
