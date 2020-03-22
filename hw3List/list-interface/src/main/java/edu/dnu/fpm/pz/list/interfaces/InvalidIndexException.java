package edu.dnu.fpm.pz.list.interfaces;

public class InvalidIndexException extends Exception {
    public InvalidIndexException() {}

    public InvalidIndexException(String message) {
        super(message);
    }
}
