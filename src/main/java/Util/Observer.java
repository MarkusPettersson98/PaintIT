package Util;

/**
 * Represents Observing objects, using the Observer-Pattern
 */


public interface Observer {

    /**
     * Gets called by {@link Observable} when it updates itself.
     */
    void update();

}
