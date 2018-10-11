import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TableView;

import java.util.Arrays;

public class Controller {



    public static void clear_local_list(){

        GUI.local_media_list.getItems().clear(); // clear old listView


    }


    public static void update_local_list(){

        GUI.local_media_list.getItems().addAll(File_Directory.fileNames); // add new filenames to list


    }


}








