package edu.dnu.fpm.pz.linkedList.core;

import edu.dnu.fpm.pz.list.interfaces.ILinkedList;

import java.util.NoSuchElementException;


public class LinkedList<T> implements ILinkedList<T> {

    protected Node<T> head;
    protected int size;

    public LinkedList() {
        size = 0;
        head = null;
    }

    @Override
    public void addFirst(T value) {
        head = new Node<T>(value, head);
        size++;
    }

    @Override
    public void addLast(T value) {
        if (head == null)
            addFirst(value);
        else {
            Node<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node<T>(value, null);
            size++;
        }
    }

    @Override
    public T removeFirst() {
        T temp = getFirst();
        head = head.next;
        size--;
        return temp;
    }

    @Override
    public T removeLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        Node<T> temp = head;
        Node<T> prev = temp;
        while (temp.next != null) {
            prev = temp;
            temp = temp.next;
        }

        T value = temp.value;
        prev.next = null;
        size--;
        return value;
    }

    @Override
    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.value;
    }

    @Override
    public T getLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        return temp.value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > getSize()) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> temp = moveToElement(index);
        return temp.value;
    }

    @Override
    public void add(int index, T value) {
        if (index < 0 || index > getSize()) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> temp = moveToElement(index);
        temp.value = value;
    }

    private Node<T> moveToElement(int index) {
        if (index < 0 || index > getSize()) {
            throw new IndexOutOfBoundsException();
        }

        if (head == null) {
            throw new NoSuchElementException();
        }

        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        return temp;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > getSize()) {
            throw new IndexOutOfBoundsException();
        }

        if (head == null) {
            throw new NoSuchElementException();
        }
        if (index == 0) {
            T value = head.value;
            head = head.next;
            return value;
        }

        Node<T> temp = head;
        Node<T> prev = temp;

        for (int i = 0; i < index; i++) {
            prev = temp;
            temp = temp.next;
        }

        prev.next = temp.next;

        size--;
        return temp.value;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node<T> temp = head;
        while (temp != null) {
            result.append("[").append(temp.value).append("]").append("->");
            temp = temp.next;
        }

        result.append("null");
        return result.toString();
    }
}

class Node<T> {
    T value;
    Node<T> next;

    public Node(T data, Node<T> next) {
        this.value = data;
        this.next = next;
    }
}
