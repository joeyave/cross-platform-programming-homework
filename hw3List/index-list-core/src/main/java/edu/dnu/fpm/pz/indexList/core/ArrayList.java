package edu.dnu.fpm.pz.indexList.core;

import edu.dnu.fpm.pz.list.interfaces.IArrayList;

public class ArrayList<T> implements IArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private T[] list;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        size = 0;
        list = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        size = 0;
        list = (T[]) new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    void expand() {
        T[] old = list;
        list = (T[]) new Object[getSize() * 2];
        if (size >= 0) {
            System.arraycopy(old, 0, list, 0, getSize());
        }
    }

    @Override
    public T change(int index, T value) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException();
        }

        T old = list[index];
        list[index] = value;
        return old;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException();
        }

        return list[index];
    }

    @Override
    public void add(int index, T value) {
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (list.length == getSize()) {
            expand();
        }

        if (getSize() - index >= 0) {
            System.arraycopy(list, index, list, index + 1, getSize() - index);
        }

        list[index] = value;
        size++;
    }


    @Override
    public T remove(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException();
        }

        T value = list[index];
        if (getSize() - 1 - index >= 0) {
            System.arraycopy(list, index + 1, list, index, getSize() - 1 - index);
        }

        size--;
        return value;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (T t : list) {
            result.append("[").append(t).append("]");
        }
        return result.toString();
    }
}
