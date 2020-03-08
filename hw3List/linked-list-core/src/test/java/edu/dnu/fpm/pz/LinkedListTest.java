package edu.dnu.fpm.pz;

import edu.dnu.fpm.pz.linkedList.core.LinkedList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.NoSuchElementException;


/**
 * Unit test for simple ILinkedList.
 */
public class LinkedListTest {
    private LinkedList<String> linkedList = new LinkedList<>();


    @Test
    public void addFirstElementShouldBecomeHead() {
        String string = "first";
        linkedList.addFirst(string);
        String first = linkedList.getFirst();

        Assert.assertEquals(string, first);
    }

    @Test
    public void addLastElementShouldBecomeTail() {
        String string = "last";
        linkedList.addLast(string);
        String last = linkedList.getLast();

        Assert.assertEquals(string, last);
    }

    @Test
    public void addElementAtIndex1ShouldPlaceItAfterFirstElement() {
        linkedList.addFirst("0");
        linkedList.addFirst("0");
        linkedList.addFirst("0");
        linkedList.addFirst("0");
        String string = "index 1 insertion";
        linkedList.add(1, string);
        String index1String = linkedList.get(1);

        Assert.assertEquals(string, index1String);
    }

    @Test
    public void removeFirstElementShouldReturnFirstElement() {
        linkedList.addFirst("last");
        linkedList.addFirst("first");
        String first = linkedList.getFirst();
        String removedFirst = linkedList.removeFirst();

        Assert.assertEquals(first, removedFirst);
    }

    @Test
    public void removeLastElementSchouldReturnLastElement() {
        linkedList.addFirst("last");
        linkedList.addFirst("first");
        String last = linkedList.getLast();
        String removedLast = linkedList.removeLast();

        Assert.assertEquals(last, removedLast);
    }

    @Test
    public void removeElementAtIndex2ShouldShiftElementsAfterRemovedOne() {
        linkedList.addFirst("0");
        linkedList.addFirst("1");
        linkedList.addFirst("2");
        linkedList.addFirst("3");
        linkedList.addFirst("4");
        String elementAtIndex3BeforeRemoval = linkedList.get(3);

        String removedElement = linkedList.remove(2);
        String elementAtIndex2AfterRemoval = linkedList.get(2);
        Assert.assertNotEquals(elementAtIndex2AfterRemoval, removedElement);

        Assert.assertEquals(elementAtIndex2AfterRemoval, elementAtIndex3BeforeRemoval);
    }

    @Test
    public void IndexOutOfBoundsExceptionTest() {
        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> linkedList.add(0, "exception")
        );

        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> linkedList.get(0)
        );

        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> linkedList.remove(0)
        );
    }

    @Test
    public void NoSuchElementExceptionTest() {
        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> linkedList.getFirst()
        );

        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> linkedList.getLast()
        );

        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> linkedList.removeFirst()
        );

        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> linkedList.removeLast()
        );
    }
}
