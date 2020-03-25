package edu.dnu.fpm.pz;

import edu.dnu.fpm.pz.linkedList.core.LinkedList;
import edu.dnu.fpm.pz.list.interfaces.InvalidIndexException;
import org.junit.*;
import org.junit.rules.ExpectedException;

/**
 * Unit test for simple ILinkedList.
 */
public class LinkedListTest {
    private LinkedList<String> linkedList;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void before() {
        linkedList = new LinkedList<>();
    }

    @After
    public void after() {
        linkedList = null;
    }

    @Test
    public void addFirstElementShouldBecomeHead() throws InvalidIndexException {
        //Given.
        String string = "first";

        //When.
        linkedList.addFirst(string);
        String first = linkedList.getFirst();

        // Then.
        Assert.assertEquals(string, first);
    }

    @Test
    public void addLastElementShouldBecomeTail() throws InvalidIndexException {
        // Given.
        String string = "last";

        // When.
        linkedList.addLast(string);
        String last = linkedList.getLast();

        // Then.
        Assert.assertEquals(string, last);
    }

    @Test
    public void addElementAtIndex1ShouldPlaceItAfterFirstElement() throws InvalidIndexException {
        //Given.
        String string = "index 1 insertion";

        // When.
        linkedList.addFirst("0");
        linkedList.addFirst("0");
        linkedList.addFirst("0");
        linkedList.addFirst("0");
        linkedList.add(1, string);
        String index1String = linkedList.get(1);

        // Then.
        Assert.assertEquals(string, index1String);
    }

    @Test
    public void removeFirstElementShouldReturnFirstElement() throws InvalidIndexException {
        // Given.
        String first = "first";

        // When.
        linkedList.addFirst("last");
        linkedList.addFirst("first");
        String removedFirst = linkedList.removeFirst();

        // Then.
        Assert.assertEquals(first, removedFirst);
    }

    @Test
    public void removeLastElementShouldReturnLastElement() throws InvalidIndexException {
        // Given.
        String expectedLast = "last";

        // When.
        linkedList.addFirst("last");
        linkedList.addFirst("first");
        String removedLast = linkedList.removeLast();

        // Then.
        Assert.assertEquals(expectedLast, removedLast);
    }

    @Test
    public void removeElementAtIndex2ShouldShiftElementsAfterRemovedOne() throws InvalidIndexException {
        // Given.
        String elementAtIndex2BeforeRemoval = "2";

        // When.
        linkedList.addFirst("4");
        linkedList.addFirst("3");
        linkedList.addFirst("2");
        linkedList.addFirst("1");
        linkedList.addFirst("0");
        String removedElement = linkedList.remove(2);
        String elementAtIndex2AfterRemoval = linkedList.get(2);

        // Then.
        Assert.assertNotEquals(elementAtIndex2BeforeRemoval, elementAtIndex2AfterRemoval);
    }

    String indexLessMessage = "Invalid index! Index was less than expected.";
    String indexMoreMessage = "Invalid index! Index was more than expected.";
    String nullHeadMessage = "Object was null!";

    @Test
    public void addAtNegativeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(indexLessMessage);

        // When.
        linkedList.add(-1, "less exception");
        thrown = ExpectedException.none();
    }

    @Test
    public void addAtTooLargeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(indexMoreMessage);

        // When.
        linkedList.add(2, "more exception");
        thrown = ExpectedException.none();
    }

    @Test
    public void getAtNegativeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(indexLessMessage);

        // When.
        linkedList.get(-1);
        thrown = ExpectedException.none();
    }

    @Test
    public void getAtTooLargeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(indexMoreMessage);

        // When.
        linkedList.get(2);
        thrown = ExpectedException.none();
    }

    @Test
    public void removeAtNegativeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(indexLessMessage);

        // When.
        linkedList.remove(-1);
        thrown = ExpectedException.none();
    }

    @Test
    public void removeAtTooLargeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(indexMoreMessage);

        // When.
        linkedList.remove(2);
        thrown = ExpectedException.none();
    }

    @Test
    public void getFirstElementOnEmptyListShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(nullHeadMessage);

        // When.
        linkedList.getFirst();
        thrown = ExpectedException.none();
    }

    @Test
    public void getLastElementOnEmptyListShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(nullHeadMessage);

        // When.
        linkedList.getLast();
        thrown = ExpectedException.none();
    }

    @Test
    public void removeFirstElementOnEmptyListShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(nullHeadMessage);

        // When.
        linkedList.removeFirst();
        thrown = ExpectedException.none();
    }

    @Test
    public void removeLastElementOnEmptyListShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(nullHeadMessage);

        // When.
        linkedList.removeLast();
        thrown = ExpectedException.none();
    }
}
