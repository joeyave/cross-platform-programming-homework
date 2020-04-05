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
        int expectedSize = 1;
        String expectedValue = "first";

        //When.
        linkedList.addFirst(expectedValue);
        String actualValue = linkedList.getFirst();
        int actualSize = linkedList.getSize();

        // Then.
        Assert.assertEquals(expectedValue, actualValue);
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void addLastElementShouldBecomeTail() throws InvalidIndexException {
        // Given.
        int expectedSize = 1;
        String expectedValue = "last";

        // When.
        linkedList.addLast(expectedValue);
        String actualValue = linkedList.getLast();
        int actualSize = linkedList.getSize();

        // Then.
        Assert.assertEquals(expectedValue, actualValue);
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void addElementAtIndex1ShouldPlaceItAfterFirstElement() throws InvalidIndexException {
        //Given.
        int expectedSize = 5;
        String expectedValue = "index 1 insertion";

        // When.
        linkedList.addFirst("0");
        linkedList.addFirst("0");
        linkedList.addFirst("0");
        linkedList.addFirst("0");
        linkedList.add(1, expectedValue);
        String actualValue = linkedList.get(1);
        int actualSize = linkedList.getSize();

        // Then.
        Assert.assertEquals(expectedValue, actualValue);
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void removeFirstElementShouldReturnFirstElement() throws InvalidIndexException {
        // Given.
        int expectedSize = 1;
        String expectedValue = "first";

        // When.
        linkedList.addFirst("last");
        linkedList.addFirst("first");
        String actualValue = linkedList.removeFirst();
        int actualSize = linkedList.getSize();

        // Then.
        Assert.assertEquals(expectedValue, actualValue);
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void removeLastElementShouldReturnLastElement() throws InvalidIndexException {
        // Given.
        int expectedSize = 1;
        String expectedValue = "last";

        // When.
        linkedList.addFirst("last");
        linkedList.addFirst("first");
        String actualValue = linkedList.removeLast();
        int actualSize = linkedList.getSize();

        // Then.
        Assert.assertEquals(expectedValue, actualValue);
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void removeElementAtIndex2ShouldShiftElementsAfterRemovedOne() throws InvalidIndexException {
        // Given.
        int expectedSize = 4;
        String expectedValue = "3";
        String expectedRemovedValue = "2";

        // When.
        linkedList.addFirst("4");
        linkedList.addFirst("3");
        linkedList.addFirst("2");
        linkedList.addFirst("1");
        linkedList.addFirst("0");
        String actualRemovedValue = linkedList.remove(2);
        String actualValue = linkedList.get(2);
        int actualSize = linkedList.getSize();

        // Then.
        Assert.assertEquals(expectedValue, actualValue);
        Assert.assertEquals(expectedRemovedValue, actualRemovedValue);
        Assert.assertEquals(expectedSize, actualSize);
    }

    String indexLessMessage = "Invalid index! Index was less than expected.";
    String indexMoreMessage = "Invalid index! Index was more than expected.";
    String nullHeadMessage = "Object was null!";

    @Test
    public void addAtNegativeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(indexLessMessage);

        // When.
        linkedList.add(-1, "less exception");
        thrown = ExpectedException.none();
    }

    @Test
    public void addAtTooLargeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(indexMoreMessage);

        // When.
        linkedList.add(2, "more exception");
        thrown = ExpectedException.none();
    }

    @Test
    public void getAtNegativeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(indexLessMessage);

        // When.
        linkedList.get(-1);
        thrown = ExpectedException.none();
    }

    @Test
    public void getAtTooLargeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(indexMoreMessage);

        // When.
        linkedList.get(2);
        thrown = ExpectedException.none();
    }

    @Test
    public void removeAtNegativeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(indexLessMessage);

        // When.
        linkedList.remove(-1);
        thrown = ExpectedException.none();
    }

    @Test
    public void removeAtTooLargeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(indexMoreMessage);

        // When.
        linkedList.remove(2);
        thrown = ExpectedException.none();
    }

    @Test
    public void getFirstElementOnEmptyListShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(nullHeadMessage);

        // When.
        linkedList.getFirst();
        thrown = ExpectedException.none();
    }

    @Test
    public void getLastElementOnEmptyListShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(nullHeadMessage);

        // When.
        linkedList.getLast();
        thrown = ExpectedException.none();
    }

    @Test
    public void removeFirstElementOnEmptyListShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(nullHeadMessage);

        // When.
        linkedList.removeFirst();
        thrown = ExpectedException.none();
    }

    @Test
    public void removeLastElementOnEmptyListShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(nullHeadMessage);

        // When.
        linkedList.removeLast();
        thrown = ExpectedException.none();
    }
}
