package edu.dnu.fpm.pz.list.interfaces;

public interface IList<T> {
    // Get list's size.
    int getSize();

    // Get element by index.
    T get(int index) throws InvalidIndexException;

    // Add new element by index. Return false if position is not empty.
    void add(int index, T value) throws InvalidIndexException;

    // Remove element by index. Return removed element.
    T remove(int index) throws InvalidIndexException;

    // Changes element by index. Returns previous element.
    T change(int index, T value) throws InvalidIndexException;

    void addFirst(T value) throws InvalidIndexException;

    void addLast(T value) throws InvalidIndexException;

    T removeFirst() throws InvalidIndexException;

    T removeLast() throws InvalidIndexException;

    T getFirst() throws InvalidIndexException;

    T getLast() throws InvalidIndexException;
}

