package edu.dnu.fpm.pz.linkedList.core;

import edu.dnu.fpm.pz.list.interfaces.ILinkedList;
import edu.dnu.fpm.pz.list.interfaces.InvalidIndexException;
import edu.dnu.fpm.pz.list.interfaces.Validator;


public class LinkedList<T> implements ILinkedList<T> {

    protected Node<T> head;
    protected int size;

    Validator<T> validator = new Validator<>(this);

    public LinkedList() {
        size = 0;
        head = null;
    }

    @Override
    public void addFirst(T value) {
        head = new Node<>(value, head);
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
            temp.next = new Node<>(value, null);
            size++;
        }
    }

    @Override
    public T removeFirst() throws InvalidIndexException {
        T temp = getFirst();
        head = head.next;
        size--;
        return temp;
    }

    @Override
    public T removeLast() throws InvalidIndexException {
        validator.headValidate(head);

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
    public T getFirst() throws InvalidIndexException {
        validator.headValidate(head);
        return head.value;
    }

    @Override
    public T getLast() throws InvalidIndexException {
        validator.headValidate(head);

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
    public T get(int index) throws InvalidIndexException {
        validator.indexValidate(index);

        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        return temp.value;
    }

    @Override
    public void add(int index, T value) throws InvalidIndexException {
        validator.indexValidate(index);

        if (index == 0) {
            addFirst(value);
            return;
        }

        Node<T> node = new Node<>(value, null);
        Node<T> temp = head;
        Node<T> prev = temp;
        for (int i = 0; i < index; i++) {
            prev = temp;
            temp = temp.next;
        }
        prev.next = node;
        node.next = temp;
    }

    @Override
    public T remove(int index) throws InvalidIndexException {
        validator.indexValidate(index);

        if (index == 0) {
            return removeFirst();
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
    public T change(int index, T value) throws InvalidIndexException {
        validator.indexValidate(index);

        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        T old = temp.value;
        temp.value = value;
        return old;
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
