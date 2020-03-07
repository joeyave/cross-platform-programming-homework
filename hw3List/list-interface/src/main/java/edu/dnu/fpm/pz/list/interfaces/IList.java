package edu.dnu.fpm.pz.list.interfaces;

public interface IList<T> {
    // Get list's size.
    int getSize();

    // Get element by index.
    T get(int index);

    // Add new element by index. Return false if position is not empty.
    void add(int index, T value);

    // Remove element by index. Return removed element.
    T remove(int index);
}

