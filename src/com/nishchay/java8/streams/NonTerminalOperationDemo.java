package com.nishchay.java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NonTerminalOperationDemo {

    public static void main(String[] args) {

        createAndPrint();

        mapDemo();
        filterDemo();
        distinctDemo();
        sortedDemo();
        limitDemo();
        skipDemo();
        peekDemo();
        noStartsWith();
        compareTwoStreams();

    }


    private static void createAndPrint() {

        Stream<String> stringStream = Stream.of("Rohit", "Shikhar", "Kohli", "Iyyar", "Rahul");
        System.out.println("########## Original List ###########");
        stringStream.forEach(System.out::println);

//                stringStream.forEach(e -> System.out.println(e));
//        Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
//        After performing a terminal operation over a stream, we can't reuse the stream

    }


    private static void mapDemo() {

        // using lambda
         /*
         Stream.of("ONE", "two", "THREE", "four", "FIVE")
                .map(value -> value.toLowerCase())
                .map(value -> value.toUpperCase())
                .forEach(s -> System.out.print(s + ", "));
         */
        // using method reference
        System.out.println("########## applying map operation over stream ###########");
        Stream.of("ONE", "two", "THREE", "four", "FIVE")
                .map(String::toLowerCase)
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    private static void filterDemo() {
        System.out.println("########## List after applying filter - filter((value) -> value.length() > 3) ###########");
        Stream.of("ONE", "two", "THREE", "four", "FIVE")
                .filter(value -> value.length() > 3)
                .forEach(System.out::println);
    }

    private static void distinctDemo() {

        // Printing only distinct numbers
        System.out.println("########## List after applying distinct() ###########");
        Stream.of(7, 1, 4, 3, 4, 3, 2, 1, 7, 2, 1, 3)
                .distinct()
                .forEach(s -> System.out.print(s + ", ")); // 7, 1, 4, 3, 2
    }

    private static void sortedDemo() {

        System.out.println("########## sorted view of the stream ###########");
        Stream.of(1, 4, 2, 7, 9, 10, 3)
                .sorted()
                .forEach(s -> System.out.print(s + ", "));

    }

    private static void limitDemo() {
        System.out.println("########## Applying limit ###########");
        Stream.of("one", "two", "three", "four", "five")
                .limit(2) // limiting stream for first two element only
                .forEach(System.out::println); // one, two
    }

    private static void skipDemo() {
        System.out.println("########## Applying skip ###########");
        IntStream.of(23, 17, 8, 12, 5, 6, 9, 16, 2)
                .skip(2) // skipping first two element only
                .filter(e -> e > 10)
                .forEach(System.out::println); //12, 16
    }

    private static void peekDemo() {

/*
        System.out.println("######### Applying peek ###########");
        Stream.of("one", "two", "three", "four", "five")
                .peek(value -> System.out.println(value));
*/

        System.out.println("######### Applying peek ###########");
        List<String> list = Stream.of("one", "two", "three", "four", "five")
                .filter(e -> e.length() > 3) // three, four, five
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        System.out.println("list = " + list);

    }


    /*
     * int arr[] = { 2, 1, 15, 10, 25, 101, 35, 19 }; (Start with 1 ) print
     * output :: 1, 15, 10, 101, 19
     * Do we have any Method like startWith(1) in Java 1.8 -> No
     *
     * */
    private static void noStartsWith() {

        int[] intArray = {2, 1, 15, 10, 25, 101, 35, 19};

        List<Integer> integerList = Arrays.stream(intArray).boxed().collect(Collectors.toList());

        List<String> result = integerList.stream().map(e -> e.toString()).filter(e -> e.startsWith("1")).collect(Collectors.toList());
        System.out.println("result = " + result);
    }

    /*
     * https://www.javaprogramto.com/2020/04/how-to-compare-two-arraylist-for-equality-in-java.html
     * */
    private static void compareTwoStreams() {

        List<Integer> list1 = Arrays.asList(23, 17, 8, 12, 5, 6, 9, 16, 2);
        List<Integer> list2 = Arrays.asList(23, 17, 8, 12, 5, 6, 9, 16, 2);


        boolean isEqualAllValues = list1.containsAll(list2);
        System.out.println("isEqualAllValues = " + isEqualAllValues);

        List<Integer> unavailable = list1.stream().filter(e -> (list2.stream().filter(d -> d.equals(e)).count()) < 1)
                .collect(Collectors.toList());

        if (unavailable.size() == 0) {
            System.out.println("list1 and list2 all values same.");
        } else {
            System.out.println("list1 and list2 all values are not  same.");
        }
    }

}
