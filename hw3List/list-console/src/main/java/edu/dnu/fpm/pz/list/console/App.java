package edu.dnu.fpm.pz.list.console;

import edu.dnu.fpm.pz.linkedList.core.LinkedList;
import edu.dnu.fpm.pz.indexList.core.IndexList;


public class App {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<String>();
        linkedList.addFirst("hello");
        linkedList.remove(0);
    }
}
