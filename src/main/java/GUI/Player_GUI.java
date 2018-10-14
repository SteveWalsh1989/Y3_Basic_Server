package GUI;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import Controller.*;
import static GUI.Main_GUI.local_media_list;

/**
 *   Media Player GUI
 *
 *  Displays media selected by user
 *
 *  Displays media controls to user
 */
public class Player_GUI {


    public static void load_player_GUI (){
        Stage media_stage = new Stage();

        // set title of stage to show media name
        media_stage.setTitle("Now Playing: " + local_media_list.getSelectionModel().getSelectedItem().toString());

        Group root = new Group();
        Scene scene = new Scene(root, 540, 210);


        // Create the media source.
        String source = ("file:/Users/Steve/Documents/DistributedSystemsProject/Local/"+local_media_list.getSelectionModel().getSelectedItem().toString());

        Media media = new Media(source);

        // Create the player and set to play automatically.
        MediaPlayer mediaPlayerView = new MediaPlayer(media);
        mediaPlayerView.setAutoPlay(true);

        Player_Controller mediaControl = new Player_Controller(mediaPlayerView);
        scene.setRoot(mediaControl);



        media_stage.setScene(scene);
        media_stage.sizeToScene();
        media_stage.show();



    }
}
