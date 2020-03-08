package edu.dnu.fpm.pz;

import edu.dnu.fpm.pz.arrayList.core.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Unit test for simple ArrayList.
 */
public class ArrayListTest {
    @Test
    public void addElementTest() {
        ArrayList<String> arrayList = new ArrayList<>(3);
        String expected = "[null][0][null]";
        arrayList.add(1, "0");

        Assert.assertEquals(arrayList.toString(), expected);
    }

    @Test
    public void changeElementShouldReturnOldElementAndInsertNewElementOnItsPlace() {
        String expected = "[0][changed][0]";

        ArrayList<String> arrayList = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            arrayList.add(i, "0");
        }

        String changedElement = arrayList.change(1, "changed");
        String newElement = arrayList.get(1);
        Assert.assertEquals(changedElement, "0");
        Assert.assertEquals(expected, arrayList.toString());
    }

    @Test
    public void removeElementShouldReturnValueAndShiftForwardElementsAfterIt() {
        String expected = "[0][0][null]";

        ArrayList<String> arrayList = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            arrayList.add(i, "0");
        }

        String beforeRemove = arrayList.get(2);
        String removedElement = arrayList.remove(2);
        Assert.assertEquals(beforeRemove, removedElement);
        Assert.assertEquals(expected, arrayList.toString());
    }

    public void IndexOutOfBoundsExceptionTest() {
        ArrayList<String> arrayList = new ArrayList<>();

        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> arrayList.add(10, "0")
        );

        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> arrayList.add(-1, "0")
        );

        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> arrayList.change(11, "0")
        );

        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> arrayList.change(-1, "0")
        );

        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> arrayList.get(-1)
        );

        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> arrayList.get(11)
        );

        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> arrayList.remove(11)
        );

        Assertions.assertThrows(
                IndexOutOfBoundsException.class,
                () -> arrayList.remove(-1)
        );
    }

}
