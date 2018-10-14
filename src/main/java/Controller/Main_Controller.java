package Controller;

import Local_Directory.Local_File_Directory;
import Server_Directory.Server_File_Directory;

import java.io.File;

import static GUI.Main_GUI.local_media_list;
import static GUI.Main_GUI.server_media_list;
import static GUI.Player_GUI.load_player_GUI;

public class Main_Controller {

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

                System.out.println("\n-- File Download successful! \n");

            } else {

                System.out.println("\n-- File failed to Download! \n");
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

                System.out.println("\n-- File Uploaded successful! \n");

            } else {

                System.out.println("\n--- File failed to Upload!  \n");

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
        //noinspection unchecked
        local_media_list.getItems().addAll(Local_File_Directory.fileNames);
        //noinspection unchecked
        server_media_list.getItems().addAll(Server_File_Directory.fileNames);

    }


}












