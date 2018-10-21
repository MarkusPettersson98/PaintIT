package Util;

/**
 * Represents observable objects, using the Observer-Pattern
 */

public interface Observable {

    /**
     * Adds an {@link Observer} that listens to the observable object.
     * @param observer The Observer of the object.
     */
    void addObserver(Observer observer);

    /**
     * Calls upon {@link Observer}s {@link Observer#update()}
     */
    void notifyObservers();
}
