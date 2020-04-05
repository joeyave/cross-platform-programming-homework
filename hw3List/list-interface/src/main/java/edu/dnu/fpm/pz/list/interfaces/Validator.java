package edu.dnu.fpm.pz.list.interfaces;

public class Validator<T> {
    private IList<T> list;

    public Validator(IList<T> list) {
        this.list = list;
    }

    public void indexValidate(int index) throws InvalidIndexException {
        if (index < 0) {
            throw new InvalidIndexException("Invalid index! Index was less than expected.");
        }
        if (index > list.getSize()) {
            throw new InvalidIndexException("Invalid index! Index was more than expected.");
        }
    }

    public void nullValidate(Object object) throws InvalidIndexException {
        if (object == null) {
            throw new InvalidIndexException("Object was null!");
        }
    }
}

