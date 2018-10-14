package Local_Directory;

/**
 * Created by Steve on 14/10/2018.
 */
public class Start_Local_Observer extends Thread {



    @Override
    public void run() {
        Local_File_Directory local_directory = new  Local_File_Directory();

        Local_Observer user = new  Local_Observer();

        local_directory.registerObserver(user);

        Local_File_Directory.fileNames =  Local_File_Directory.getFileNamesFromDirectory();

        Local_File_Directory.printFileNames(  Local_File_Directory.fileNames);


        while(true) {

            Local_File_Directory.compareDirectory();

            if (  Local_File_Directory.hasChanged) {

                local_directory.notifyObservers();

            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}



