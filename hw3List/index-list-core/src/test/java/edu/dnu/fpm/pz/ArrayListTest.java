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
        // Given
        int expectedCapacity = 5;

        // When
        arrayList = new ArrayList<>(5);
        int actualCapacity = arrayList.getLength();

        // Then
        Assert.assertEquals(expectedCapacity, actualCapacity);
    }

    @Test
    public void addElementTest() throws InvalidIndexException {
        // Given
        String expectedValue = "new";
        int expectedSize = 1;

        // When
        arrayList.add(0, "new");
        String actualValue = arrayList.getFirst();
        int actualSize = arrayList.getSize();

        // Then
        Assert.assertEquals(expectedValue, actualValue);
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void changeElementShouldReturnOldElementAndInsertNewElementOnItsPlace() throws InvalidIndexException {
        // Given
        int expectedSize = 3;
        String givenValue = "nothing";
        arrayList.addFirst(givenValue);
        arrayList.addFirst(givenValue);
        arrayList.addFirst(givenValue);

        // When
        String expectedValue = "changed";
        String oldValue = arrayList.change(1, expectedValue);
        int actualSize = arrayList.getSize();
        String actualValue = arrayList.get(1);

        // Then
        Assert.assertEquals(oldValue, givenValue);
        Assert.assertEquals(expectedValue, actualValue);
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void removeElementShouldReturnValueAndShiftElementsAfterIt() throws InvalidIndexException {
        // Given
        int expectedSize = 2;
        String givenValue = "nothing";
        arrayList.addFirst(givenValue);
        arrayList.addFirst(givenValue);
        arrayList.addFirst(givenValue);

        // When
        String removedValue = arrayList.remove(1);
        String actualValue = arrayList.get(2);
        int actualSize = arrayList.getSize();

        // Then
        Assert.assertEquals(removedValue, givenValue);
        Assert.assertNull(actualValue);
        Assert.assertEquals(expectedSize, actualSize);
    }


    String INDEX_LESS_MESSAGE = "Invalid index! Index was less than expected.";
    String INDEX_MORE_MESSAGE = "Invalid index! Index was more than expected.";
    String NULL_OBJECT_MESSAGE = "Object was null!";

    @Test
    public void addAtNegativeIndexShouldThrowException() throws InvalidIndexException {
        // Given
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(INDEX_LESS_MESSAGE);

        // When
        arrayList.add(-1, "less exception");
        thrown = ExpectedException.none();
    }

    @Test
    public void addAtTooLargeIndexShouldThrowException() throws InvalidIndexException {
        // Given
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(INDEX_MORE_MESSAGE);

        // When
        arrayList.add(100, "more exception");
        thrown = ExpectedException.none();
    }

    @Test
    public void getAtNegativeIndexShouldThrowException() throws InvalidIndexException {
        // Given
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(INDEX_LESS_MESSAGE);

        // When
        arrayList.get(-1);
        thrown = ExpectedException.none();
    }

    @Test
    public void getAtTooLargeIndexShouldThrowException() throws InvalidIndexException {
        // Given
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(INDEX_MORE_MESSAGE);

        // When
        arrayList.get(100);
        thrown = ExpectedException.none();
    }

    @Test
    public void removeAtNegativeIndexShouldThrowException() throws InvalidIndexException {
        // Given
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(INDEX_LESS_MESSAGE);

        // When
        arrayList.remove(-1);
        thrown = ExpectedException.none();
    }

    @Test
    public void removeAtTooLargeIndexShouldThrowException() throws InvalidIndexException {
        // Given
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(INDEX_MORE_MESSAGE);

        // When
        arrayList.remove(100);
        thrown = ExpectedException.none();
    }

    @Test
    public void getFirstElementOnEmptyListShouldThrowException() throws InvalidIndexException {
        // Given
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(NULL_OBJECT_MESSAGE);

        // When
        arrayList.getFirst();
        thrown = ExpectedException.none();
    }

    @Test
    public void getLastElementOnEmptyListShouldThrowException() throws InvalidIndexException {
        // Given
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(INDEX_LESS_MESSAGE);

        // When
        arrayList.getLast();
        thrown = ExpectedException.none();
    }

    @Test
    public void removeFirstElementOnEmptyListShouldThrowException() throws InvalidIndexException {
        // Given
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(NULL_OBJECT_MESSAGE);

        // When
        arrayList.removeFirst();
        thrown = ExpectedException.none();
    }

    @Test
    public void removeLastElementOnEmptyListShouldThrowException() throws InvalidIndexException {
        // Given
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(INDEX_LESS_MESSAGE);

        // When
        arrayList.removeLast();
        thrown = ExpectedException.none();
    }

    @Test
    public void changeAtNegativeIndexShouldThrowException() throws InvalidIndexException {
        // Given
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(INDEX_LESS_MESSAGE);

        // When
        arrayList.change(-1, "change");
        thrown = ExpectedException.none();
    }

    @Test
    public void changeAtTooLargeIndexShouldThrowException() throws InvalidIndexException {
        // Given
        thrown.expect(InvalidIndexException.class);
        thrown.expectMessage(INDEX_MORE_MESSAGE);

        // When
        arrayList.change(100, "change");
        thrown = ExpectedException.none();
    }
}
