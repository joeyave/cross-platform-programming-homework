package edu.dnu.fpm.pz;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws UnirestException {
        System.out.println(listOfIntsToString(Arrays.asList(14, 152, 12, 153, 157, 519)));

        LinkedList<City> citiesLinkedList = new LinkedList<>();
        fillUpCitiesList(citiesLinkedList);
        Map<String, City> cityMap = getLargestCityPerState(citiesLinkedList);
        printStates(cityMap);

        zip(Stream.of(1, 3, 5), Stream.of(2, 4, 6, 8)).forEach(System.out::println);
    }

    // Task 1.
    public static String listOfIntsToString(List<Integer> list) {
        Stream<Integer> stream = list.stream();

        return stream.map(x -> (x % 2 == 0 ?
                "e" + x.toString() :
                "o" + x.toString())).collect(Collectors.joining(", "));
    }

    // Task 2.
    public static Map<String, City> getLargestCityPerState(List<City> list) {
        Stream<City> stream = list.stream();
        Map<String, List<City>> states = stream.collect(Collectors.groupingBy(City::getState));

        return states.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .max(Comparator.comparing(City::getPopulation))
                                .orElseThrow(NoSuchElementException::new)
                ));
    }

    public static void fillUpCitiesList(List<City> list) throws UnirestException {
        String host = "https://public.opendatasoft.com/api/records/1.0/search/" +
                "?dataset=1000-largest-us-cities-by-population-with-geographic-coordinates" +
                "&rows=1000&facet=city" +
                "&facet=state" +
                "&facet=population";
        HttpResponse<JsonNode> response = Unirest.get(host).asJson();
        JSONObject jsonObject = response.getBody().getObject();
        JSONArray records = (JSONArray) jsonObject.get("records");

        for (Object record : records) {
            JSONObject recordJsonArray = (JSONObject) record;
            JSONObject fields = (JSONObject) recordJsonArray.get("fields");
            String city = fields.getString("city");
            String state = fields.getString("state");
            long population = fields.getInt("population");

            list.add(new City(city, state, population));
        }
    }

    public static void printStates(Map<String, City> cityMap) {
        String result = cityMap.entrySet()
                .stream()
                .map(e -> e.getKey() + "=\"" + e.getValue() + "\"")
                .collect(Collectors.joining("\n"));
        System.out.println(result);
    }

    //Task 3.
    public static Stream<Integer> zip(Stream<Integer> first, Stream<Integer> second) {
        Iterator<Integer> it1 = first.iterator();
        Iterator<Integer> it2 = second.iterator();
        Stream<Integer> result = Stream.empty();

        while (it1.hasNext() && it2.hasNext()) {
            result = Stream.concat(result, Stream.of(it1.next(), it2.next()));
        }

        return result;
    }
}
