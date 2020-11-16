package com.nishchay.java8.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TerminalOperationDemo {

    public static void main(String[] args) {

//        mthd4AnyMatchAllMatchNoMatch();
//        mthd4Collect();
//        mthd4Count();
//        mthd4FindAnyFindFist();
//        mthd4StreamForEach();
//        mthd4MinMax();
//        mthd4Reduce();
//        streamToObjectArray();
//        mthd4ConcatenateStreams();

//        mthd4PrimitiveArrayToStreamAndBack();

    }



    private static void mthd4AnyMatchAllMatchNoMatch() {

        List<String> stringList = new ArrayList<>();
        stringList.add("One flew over the cuckoo's nest");
        stringList.add("To kill a muckingbird");
        stringList.add("Gone with the wind");

        boolean anyMatch = stringList.stream()
                .anyMatch( value ->  value.startsWith("One"));
        System.out.println("anyMatch - " + anyMatch);

        boolean allMatch = stringList.stream()
                .allMatch(value -> value.startsWith("One"));
        System.out.println("allMatch - " + allMatch);

        boolean noneMatch = stringList.stream()
                .noneMatch(element -> element.startsWith("Two"));

        System.out.println("noneMatch = " + noneMatch);

    }



    private static void mthd4Collect() {

        List<String> strUpperCaseList =
                Stream.of("one", "two", "three", "four", "five")
                        .map(value -> value.toUpperCase())
                        .collect(Collectors.toList());
        System.out.println(strUpperCaseList);
    }


    private static void mthd4Count() {

        long count = Stream.of("one", "two", "three", "four", "five")
                .filter(e -> e.startsWith("t"))
                .count();

        System.out.println("count = " + count);

    }

    private static void mthd4FindAnyFindFist() {

        // Find any -  but its always giving the first element only
        Optional<String> anyElement =
                            Stream.of("one", "two", "three", "four", "five")
                            .findAny();
        System.out.println("Find any - " + anyElement.get());

        // Find first
        Optional<String> firstElement =
                Stream.of("nine", "ten")
                        .findFirst();

        System.out.println("Find first - " + firstElement.get());
    }

    private static void mthd4StreamForEach() {
        Stream<String> stream = Stream.of("fox","elephant","lion","tiger","bear");
        stream.forEach(element -> System.out.println(element));
    }



    private static void mthd4MinMax() {
        System.out.println("########## MinMax - String ###########");
        List<String> animalList = Arrays.asList("be","elephant","lion","tiger","ant");
        System.out.println("minString - " + animalList.stream().min(Comparator.comparing(s -> s.length())).get());
        System.out.println("maxString - " + animalList.stream().max((s1,s2) -> s1.length() - s2.length()).get());

        // MinMaxInteger
        System.out.println("########## MinMax - Integer ###########");
        List<Integer> numbers = Arrays.asList(14, 9, 12, 3, 10, 4, 20, 28);
        System.out.println("min - " + numbers.stream().min((o1,o2) -> o1.compareTo(o2)).get());
        System.out.println("max - " + numbers.stream().max(Integer::compare).get());

    }



    private static void mthd4Reduce() {

        // Applying reduce over IntStream

        OptionalInt optionalIntSum = IntStream.of(7, 5, 9, 2, 8, 1).reduce((a, b) -> a + b);
        System.out.println("optionalIntSum = " + optionalIntSum);
        // if you pass the initial value, it will give you the result instead of optional - here int
        int intSum = Arrays.stream(new int[]{7, 5, 9, 2, 8, 1}).reduce(0, (a, b) -> a + b);
        System.out.println("intSum = " + intSum);

        // Applying reduce over Stream of Integer
        Optional<Integer> optionalSum = Stream.of(7, 5, 9, 2, 8, 1).reduce((a, b) -> a + b);
        System.out.println("optionalSum = " + optionalSum);
        // if you pass the initial value, it will give you the result instead of optional - here Integer
        Integer integerSum = Stream.of(7, 5, 9, 2, 8, 1).reduce(0,(a, b) -> a + b);
        System.out.println("integerSum = " + integerSum);

        // Applying reduce over Stream of String
        Optional<String> reduced = Stream.of("one", "two", "three", "four", "five").
                reduce((value, combinedValue) -> value + " + " + combinedValue);
        System.out.println("reduced value - " + reduced.get());
    }

    private static void streamToObjectArray() {

        Object[] objArray = Arrays.asList("fox","elephant","lion","tiger","bear").toArray();
        System.out.println("objects[] = " + Arrays.toString(objArray));

    }

    private static void mthd4ConcatenateStreams() {

        Stream<String> streamOne = Stream.of("one", "two", "three", "four", "five");
        Stream<String> streamTwo = Stream.of("six", "seven", "eight", "nine", "ten");

        Stream<String> concatStream = Stream.concat(streamOne, streamTwo);

        List<String> stringsAsUppercaseList = concatStream
                .collect(Collectors.toList());

        System.out.println(stringsAsUppercaseList);
    }

    private static void mthd4PrimitiveArrayToStreamAndBack() {

        int intArr[] = {1, 9, 3, 10, 4, 20, 2};
        Set<Integer> intSet = Arrays.stream(intArr)
                .boxed()
                .collect(Collectors.toSet());

        System.out.println("intSet - " + intSet);

        int[] array = intSet.stream().mapToInt(i->i).toArray();
        System.out.println("array = " + Arrays.toString(array));
    }




}