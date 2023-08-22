package vn.ha.tower_defense.observers;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface Subject {

    void attach(Observer observer);

    void attachAll(List<? extends Observer> observers);


    void detach(Observer observer);

    void notifyAllObserver(Event event);
}