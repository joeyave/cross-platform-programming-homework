package edu.dnu.fpm.pz.list.interfaces;

public interface ILinkedList<T> extends IList<T> {
    void addFirst(T value);

    void addLast(T value);

    T removeFirst();

    T removeLast();

    T getFirst();

    T getLast();
}
