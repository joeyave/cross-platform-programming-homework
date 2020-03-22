package edu.dnu.fpm.pz.list.console;


import edu.dnu.fpm.pz.arrayList.core.ArrayList;
import edu.dnu.fpm.pz.linkedList.core.LinkedList;
import edu.dnu.fpm.pz.list.interfaces.IList;
import edu.dnu.fpm.pz.list.interfaces.InvalidIndexException;

public class App {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        ArrayList<String> arrayList = new ArrayList<>();

        try {
            listTest(linkedList);
            listTest(arrayList);
        } catch (InvalidIndexException e) {
            e.printStackTrace();
        }
    }

    public static void listTest(IList<String> list) throws InvalidIndexException {
        list.addFirst("*");
        list.addFirst("*");
        list.addLast("!");
        list.addFirst("*");
        list.addFirst("*");
        list.add(1, "hello :)");
        list.change(1, "good bye :(");
        list.remove(2);
        list.removeFirst();
        list.removeLast();
        System.out.println(list.toString());
    }
}
