package vn.ha.tower_defense.collections;

import java.util.Collection;

public interface Matrix<T> {
    Collection<T> flatten();

    T getElement(int y, int x);

    void putElement(int y, int x, T element);

    void resetElement(int y, int x);

    void resetAll();
}
