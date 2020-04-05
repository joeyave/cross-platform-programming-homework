package edu.dnu.fpm.pz.list.interfaces;

public class InvalidIndexException extends Exception {
    public InvalidIndexException(String message) {
        super(message);
    }

    public InvalidIndexException() {
        super();
    }

    public InvalidIndexException(String message, Throwable cause) {
        super(message, cause);
    }

    public void toConsole() {
        System.out.println("Class: " + getClass() + "\nMessage: " + getMessage());
    }
}
