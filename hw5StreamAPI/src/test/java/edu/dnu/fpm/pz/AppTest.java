package edu.dnu.fpm.pz;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Stream;


/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void listOfIntsToStringTest() {
        // Given
        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(14, 152, 12, 153, 157, 519));
        String expectedString = "e14, e152, e12, o153, o157, o519";
        // When
        String actualString = App.listOfIntsToString(linkedList);

        // Then
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void getLargestCityPerStateTest() {
        // Given
        // Cities.
        City city1 = new City("City1", "State1", 1000000);
        City city2 = new City("City2", "State1", 900000);
        City city3 = new City("City3", "State1", 800000);

        City city4 = new City("City1", "State2", 800000);
        City city5 = new City("City2", "State2", 700000);
        City city6 = new City("City3", "State2", 1100000);

        City city7 = new City("City1", "State3", 600000);
        City city8 = new City("City2", "State3", 6600000);
        City city9 = new City("City3", "State3", 1200000);


        LinkedList<City> citiesLinkedList = new LinkedList<>();
        // State 1.
        citiesLinkedList.add(city1);
        citiesLinkedList.add(city2);
        citiesLinkedList.add(city3);

        // State 2.
        citiesLinkedList.add(city4);
        citiesLinkedList.add(city5);
        citiesLinkedList.add(city6);

        // State 3.
        citiesLinkedList.add(city7);
        citiesLinkedList.add(city8);
        citiesLinkedList.add(city9);

        HashMap<String, City> expectedCityMap = new HashMap<>(Map.of(
                "State1", city1,
                "State2", city6,
                "State3", city8
        ));

        // When
        Map<String, City> actualCityMap = App.getLargestCityPerState(citiesLinkedList);

        // Then
        Assert.assertEquals(expectedCityMap, actualCityMap);
    }

    @Test
    public void zipTest() {
        // Given
        Stream<Integer> stream1 = Stream.of(1, 3, 5);
        Stream<Integer> stream2 = Stream.of(2, 4, 6, 8);
        Stream<Integer> expectedStream = Stream.of(1, 2, 3, 4, 5, 6);

        // When
        Stream<Integer> actualStream = App.zip(stream1, stream2);

        // Then
        Assert.assertArrayEquals(expectedStream.toArray(), actualStream.toArray());
    }
}
