package edu.dnu.fpm.pz.list.interfaces;

public interface List {
    void add(Object data);

    Object get(int index);

    boolean remove(int index);

    int size();

    String toString();
}

