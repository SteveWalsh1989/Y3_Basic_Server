package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;


import static GUI.Main_GUI.local_media_list;

/**
 * Created by Steve on 14/10/2018.
 */
public class Player_GUI {

    public static VBox mainScreen;
    public static Scene playerScene;


    public static void load_player_GUI (){

        Stage playerStage = new Stage();

        mainScreen = new VBox();

        playerScene = new Scene(mainScreen, 800, 450);

        mainScreen.setAlignment(Pos.CENTER);                                            // center labels and buttons

        mainScreen.setSpacing(25);                                                      // add spacing between elements

        mainScreen.setPadding(new Insets(10,10,10,10));          // add padding around sections

        //----------------------
        // Media Player
        //----------------------

        StackPane mediaContainer = new StackPane();


        // Create the media source.
        String source = ("file:/Users/Steve/Documents/DistributedSystemsProject/Local/"+local_media_list.getSelectionModel().getSelectedItem().toString());

        Media media = new Media(source);

        // Create the player and set to play automatically.
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        // Create the view and add it to the Scene.
        MediaView mediaView = new MediaView(mediaPlayer);



        mediaContainer.getChildren().add(mediaView);


        mainScreen.getChildren().addAll(mediaContainer);


        playerStage.setTitle("Media Player");                       // set title of primary stage

        playerStage.setScene(playerScene);

        playerStage.setMaxWidth(650);
        playerStage.setMinWidth(450);

        playerStage.setMaxHeight(700);
        playerStage.setMinHeight(500);

        playerStage.show();


    }
}
