package edu.dnu.fpm.pz;

import edu.dnu.fpm.pz.arrayList.core.ArrayList;
import edu.dnu.fpm.pz.list.interfaces.InvalidIndexException;
import org.junit.*;
import org.junit.rules.ExpectedException;

/**
 * Unit test for simple ArrayList.
 */
public class ArrayListTest {
    private ArrayList<String> arrayList;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void before() {
        arrayList = new ArrayList<>(3);
    }

    @After
    public void after() {
        arrayList = null;
    }

    @Test
    public void initializeWithDefaultConstructorShouldSetCapacity() {
        // Given.
        int expectedCapacity = 5;

        // When.
        arrayList = new ArrayList<>(5);

        // Then.
        Assert.assertEquals(expectedCapacity, arrayList.getLength());
    }

    @Test
    public void addElementTest() throws InvalidIndexException {
        // Given.
        String expected = "[new][null][null]";

        // When.
        arrayList.add(0, "new");

        // Then.
        Assert.assertEquals(arrayList.toString(), expected);
    }

    @Test
    public void changeElementShouldReturnOldElementAndInsertNewElementOnItsPlace() throws InvalidIndexException {
        // Given.
        String expected = "[0][changed][0]";
        for (int i = 0; i < 3; i++) {
            arrayList.add(i, "0");
        }

        // When.
        String changedElement = arrayList.change(1, "changed");

        // Then.
        Assert.assertEquals(changedElement, "0");
        Assert.assertEquals(expected, arrayList.toString());
    }

    @Test
    public void removeElementShouldReturnValueAndShiftElementsAfterIt() throws InvalidIndexException {
        // Given.
        String expected = "[0][0][null]";
        for (int i = 0; i < 3; i++) {
            arrayList.add(i, "0");
        }

        // When.
        String removedElement = arrayList.remove(1);

        // Then.
        Assert.assertEquals(expected, arrayList.toString());
    }


    String indexLessMessage = "Invalid index! Index was less than expected.";
    String indexMoreMessage = "Invalid index! Index was more than expected.";
    String nullObjectMessage = "Object was null!";

    @Test
    public void addAtNegativeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(indexLessMessage);

        // When.
        arrayList.add(-1, "less exception");
        thrown = ExpectedException.none();
    }

    @Test
    public void addAtTooLargeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(indexMoreMessage);

        // When.
        arrayList.add(100, "more exception");
        thrown = ExpectedException.none();
    }

    @Test
    public void getAtNegativeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(indexLessMessage);

        // When.
        arrayList.get(-1);
        thrown = ExpectedException.none();
    }

    @Test
    public void getAtTooLargeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(indexMoreMessage);

        // When.
        arrayList.get(100);
        thrown = ExpectedException.none();
    }

    @Test
    public void removeAtNegativeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(indexLessMessage);

        // When.
        arrayList.remove(-1);
        thrown = ExpectedException.none();
    }

    @Test
    public void removeAtTooLargeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(indexMoreMessage);

        // When.
        arrayList.remove(100);
        thrown = ExpectedException.none();
    }

    @Test
    public void getFirstElementOnEmptyListShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(nullObjectMessage);

        // When.
        arrayList.getFirst();
        thrown = ExpectedException.none();
    }

    @Test
    public void getLastElementOnEmptyListShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(indexLessMessage);

        // When.
        arrayList.getLast();
        thrown = ExpectedException.none();
    }

    @Test
    public void removeFirstElementOnEmptyListShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(nullObjectMessage);

        // When.
        arrayList.removeFirst();
        thrown = ExpectedException.none();
    }

    @Test
    public void removeLastElementOnEmptyListShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(indexLessMessage);

        // When.
        arrayList.removeLast();
        thrown = ExpectedException.none();
    }

    @Test
    public void changeAtNegativeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(indexLessMessage);

        // When.
        arrayList.change(-1, "change");
        thrown = ExpectedException.none();
    }

    @Test
    public void changeAtTooLargeIndexShouldThrowException() throws InvalidIndexException {
        // Given.
        thrown.expectMessage(indexMoreMessage);

        // When.
        arrayList.change(100, "change");
        thrown = ExpectedException.none();
    }
}
