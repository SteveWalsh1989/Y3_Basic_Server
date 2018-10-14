package Server_Directory;

/**
 * Created by Steve on 10/10/2018.
 */
public class Start_Server_Observer extends Thread {


    @Override
    public void run() {
            Server_File_Directory server_directory = new Server_File_Directory();

            Server_Observer user = new Server_Observer();

            server_directory.registerObserver(user);

            Server_File_Directory.fileNames = Server_File_Directory.getFileNamesFromDirectory();

            Server_File_Directory.printFileNames( Server_File_Directory.fileNames);


        while(true) {

                Server_File_Directory.compareDirectory();

                if ( Server_File_Directory.hasChanged) {

                    server_directory.notifyObservers();

                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

