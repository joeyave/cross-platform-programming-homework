package edu.dnu.fpm.pz.linkedList.core;

import edu.dnu.fpm.pz.list.interfaces.List;

public class LinkedList implements List {
    private int size;
    private Node head;

    public LinkedList() {
        this.size = 0;
        this.head = null;
    }

    @Override
    public void add(Object data) {
        if (head == null) {
            head = new Node(data);
        }

        Node temp = new Node(data);
        Node current = head;

        if (current != null) {
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(temp);
            size++;
        }
    }

    @Override
    public Object get(int index) {
        // TODO: check if index is valid.

        Node current = null;
        if (head != null) {
            current = head.getNext();

            for (int i = 0; i < index; i++) {
                if (current.getNext() == null) {
                    return null;
                }
                current = current.getNext();
            }
            return current.getData();
        }
        return null;
    }

    @Override
    public boolean remove(int index) {
        // TODO: check if index is valid.

        Node current = head;
        if (head != null) {
            for (int i = 0; i < index; i++) {
                if (current.getNext() == null)
                    return false;

                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());

            size--;
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        if (head != null) {
            Node current = head.getNext();
            output.append("head->");
            while (current != null) {
                output.append("[").append(current.getData().toString()).append("]->");
                current = current.getNext();
            }
            output.append("null");
        } else {
            output.append("list is empty");
        }
        return output.toString();
    }
}

class Node {
    Node next;
    Object data;

    public Node(Object dataValue) {
        next = null;
        data = dataValue;
    }

    public Node(Object dataValue, Node nextValue) {
        next = nextValue;
        data = dataValue;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object dataValue) {
        data = dataValue;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node nextValue) {
        next = nextValue;
    }
}
