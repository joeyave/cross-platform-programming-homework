package edu.dnu.fpm.pz.list.console;


import edu.dnu.fpm.pz.linkedList.core.LinkedList;

public class App {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.addFirst("0");
        linkedList.addLast("0");
        linkedList.add(1, "hello");
    }
}
