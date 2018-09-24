package Tools;

import java.util.ArrayList;
import java.util.List;

public interface Observable {

    void addObserver(Observer observer);

    void notifyObservers();
}
