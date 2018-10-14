package Interfaces;

/**
 * Created by Steve on 03/10/2018.
 */
public interface Subject_Interface {


     void registerObserver(Observer_Interface observer);

     void removeObserver(Observer_Interface observer);

     void notifyObservers();



}
