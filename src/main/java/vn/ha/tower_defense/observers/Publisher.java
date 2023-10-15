package vn.ha.tower_defense.observers;

import java.util.List;

public interface Publisher {

    void attach(Observer observer);

    void attachAll(List<? extends Observer> observers);

    void detach(Observer observer);

    void notifyAll(Event event);

    void detachAll();
}