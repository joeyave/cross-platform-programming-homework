package edu.dnu.fpm.pz.list.interfaces;

public interface IArrayList<T> extends IList<T> {
    // Changes element by index. Returns previous element.
    T change(int index, T value) throws InvalidIndexException;
}
