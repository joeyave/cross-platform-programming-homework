package edu.dnu.fpm.pz.list.interfaces;

public interface ILinkedList<T> extends IList<T> {
    void addFirst(T value);

    void addLast(T value);

    T removeFirst() throws InvalidIndexException;

    T removeLast() throws InvalidIndexException;

    T getFirst() throws InvalidIndexException;

    T getLast() throws InvalidIndexException;
}
