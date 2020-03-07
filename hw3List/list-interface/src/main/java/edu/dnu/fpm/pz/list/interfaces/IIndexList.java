package edu.dnu.fpm.pz.list.interfaces;

public interface IIndexList<T> extends IList<T> {
    // Changes element by index. Returns previous element.
    T exchange(int index, T value);
}
