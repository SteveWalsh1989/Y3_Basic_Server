package Controller;

import GUI.*;
import Local_Directory.Local_File_Directory;
import Server_Directory.Server_File_Directory;
import javafx.event.EventHandler;

import java.awt.event.ActionEvent;
import java.io.File;

import static GUI.Main_GUI.local_media_list;
import static GUI.Main_GUI.server_media_list;
import static GUI.Player_GUI.load_player_GUI;

public class Controller {

    /**
     * download
     * <p>
     * Downloads files from server directory to local directory
     * <p>
     * Selects file from server and renames its file path
     */
    public static void download() {

        // stores file source name
        String fileSrc = ("/Users/Steve/Documents/DistributedSystemsProject/Server/" + server_media_list.getSelectionModel().getSelectedItem().toString());

        // System.out.print(fileSrc);  // TEST: print src name to console
        try {

            // Select file using source
            File afile = new File(fileSrc);

            // rename file to be stored in local directory
            if (afile.renameTo(new File("/Users/Steve/Documents/DistributedSystemsProject/Local/" + afile.getName()))) {
                System.out.println("File Download successful!");
            } else {
                System.out.println("File failed to Download!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        update();
    }

    /**
     * upload
     * <p>
     * upload files from local directory to server directory
     * <p>
     * Selects file from local and renames its file path
     */
    public static void upload(){
        // stores file source name
        String fileSrc = ("/Users/Steve/Documents/DistributedSystemsProject/Local/" + local_media_list.getSelectionModel().getSelectedItem().toString());

        // System.out.print(fileSrc);  // TEST: print src name to console
        try {

            // Select file using source
            File afile = new File(fileSrc);

            // rename file to be stored in local directory
            if (afile.renameTo(new File("/Users/Steve/Documents/DistributedSystemsProject/Server/" + afile.getName()))) {
                System.out.println("File Uploaded successful!");
            } else {
                System.out.println("File failed to Upload!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        update();
    }

    public static void play() {

        load_player_GUI(); // Load Media Player

    }


    public static void update(){
        local_media_list.getItems().clear();
        server_media_list.getItems().clear();
        local_media_list.getItems().addAll(Local_File_Directory.fileNames);
        server_media_list.getItems().addAll(Server_File_Directory.fileNames);

    }


}












