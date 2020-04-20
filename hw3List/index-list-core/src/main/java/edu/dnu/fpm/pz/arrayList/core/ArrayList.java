package edu.dnu.fpm.pz.arrayList.core;

import edu.dnu.fpm.pz.list.interfaces.IList;
import edu.dnu.fpm.pz.list.interfaces.InvalidIndexException;
import edu.dnu.fpm.pz.list.interfaces.Validator;

public class ArrayList<T> implements IList<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private T[] list;

    Validator<T> validator = new Validator<>(this);

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
    public T change(int index, T value) throws InvalidIndexException {
        validator.indexValidate(index);

        T old = list[index];
        list[index] = value;
        return old;
    }

    @Override
    public void addFirst(T value) throws InvalidIndexException {
        add(0, value);
    }

    @Override
    public void addLast(T value) throws InvalidIndexException {
        add(size, value);
    }

    @Override
    public T removeFirst() throws InvalidIndexException {
        validator.nullValidate(list[0]);
        return remove(0);
    }

    @Override
    public T removeLast() throws InvalidIndexException {
        validator.indexValidate(size - 1);
        return remove(size - 1);
    }

    @Override
    public T getFirst() throws InvalidIndexException {
        validator.nullValidate(list[0]);
        return list[0];
    }

    @Override
    public T getLast() throws InvalidIndexException {
        validator.indexValidate(size - 1);
        return list[size - 1];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public T get(int index) throws InvalidIndexException {
        validator.indexValidate(index);

        return list[index];
    }

    @Override
    public void add(int index, T value) throws InvalidIndexException {
        validator.indexValidate(index);

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
    public T remove(int index) throws InvalidIndexException {
        validator.indexValidate(index);

        T value = list[index];
        if (getSize() - 1 - index >= 0) System.arraycopy(list, index + 1, list, index, getSize() - 1 - index);
        list[getSize() - 1] = null;
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

    public int getLength() {
        return list.length;
    }
}
