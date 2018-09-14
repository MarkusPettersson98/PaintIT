package Tools;

import java.util.ArrayList;
import java.util.List;

public interface Observable {

    List<Observer> Observers = new ArrayList<>();

    void addObserver(Observer observer);
}
