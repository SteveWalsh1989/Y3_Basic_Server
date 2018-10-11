/**
 * Created by Steve on 10/10/2018.
 */
public class StartObserver extends Thread {


    @Override
    public void run() {
            File_Directory server_directory = new File_Directory();

            User_Observer user = new User_Observer();

            server_directory.registerObserver(user);

            File_Directory.fileNames = File_Directory.getFileNamesFromDirectory();

            File_Directory.printFileNames( File_Directory.fileNames);


        while(true) {

                File_Directory.compareDirectory();

                if ( File_Directory.hasChanged) {

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

