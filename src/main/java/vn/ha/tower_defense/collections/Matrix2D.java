package vn.ha.tower_defense.collections;

import java.util.ArrayList;
import java.util.Collection;

public class Matrix2D<T> implements Matrix<T> {

    private T[][] matrix;

    public Matrix2D(T[] elements, int width) {
        intiateMatrix(elements, width);
    }

    private void intiateMatrix(T[] elements, int width) {
        int height = (elements.length / width) + 1;
        this.matrix = (T[][]) new Object[height][width];

        int currentIndex = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j <  width; j++) {
                T nextElement = elements[currentIndex];
                if(currentIndex == elements.length) {
                    nextElement = null;
                }
                this.matrix[i][j] = nextElement;
                currentIndex++;
            }
        }
    }

    @Override
    public Collection<T> flatten() {
        Collection<T> elements = new ArrayList<>();

//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j <  width; j++) {
//                T nextElement = elements[currentIndex];
//                if(currentIndex == elements.length) {
//                    nextElement = null;
//                }
//                this.matrix[i][j] = nextElement;
//                currentIndex++;
//            }
//        }

        return null;
    }

    @Override
    public T getElement(int y, int x) {
        return null;
    }

    @Override
    public void putElement(int y, int x, T element) {

    }

    @Override
    public void resetElement(int y, int x) {

    }

    @Override
    public void resetAll() {

    }
}
