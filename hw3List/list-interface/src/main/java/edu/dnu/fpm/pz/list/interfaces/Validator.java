package edu.dnu.fpm.pz.list.interfaces;

public class Validator<T> {
    private IList<T> list;

    public Validator(IList<T> list) {
        this.list = list;
    }

    public void indexValidate(int index) throws InvalidIndexException {
        if (index < 0 || index > list.getSize()) {
            throw new InvalidIndexException("Invalid index!");
        }
    }

    public void headValidate(Object head) throws InvalidIndexException {
        if (head == null) {
            throw new InvalidIndexException("Head was null!");
        }
    }
}

