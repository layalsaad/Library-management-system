package IteratorDP;

public interface Iterator<T> {
    boolean hasNext();
    void next();
    T getNext();
}
