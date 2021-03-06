package com.nishchay.java8.streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NonTerminalOperationDemo {

    public static void main(String[] args) {

//        createAndPrint();

//        mapDemo();
//        filterDemo();
//        distinctDemo();
//        sortedDemo();
        limitDemo();
        skipDemo();
//        peekDemo();

    }

    private static void createAndPrint(){

        Stream<String> stringStream = Stream.of("Rohit", "Shikhar", "Kohli", "Iyyar", "Rahul");
        System.out.println("########## Original List ###########");
        stringStream.forEach(System.out::println);

//                stringStream.forEach(e -> System.out.println(e));
//        Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
//        After performing a terminal operation over a stream , we can't reuse the stream

    }


    private static void mapDemo(){

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


    private static void filterDemo(){
        System.out.println("########## List after applying filter - filter((value) -> value.length() > 3) ###########");
        Stream.of("ONE", "two", "THREE", "four", "FIVE")
                .filter(value -> value.length() > 3)
                .forEach(System.out::println);
    }

    private static void distinctDemo(){

        // Printing only distinct numbers
        System.out.println("########## List after applying distinct() ###########");
        Stream.of(7, 1, 4, 3, 4, 3, 2, 1, 7, 2, 1, 3)
                .distinct()
                .forEach(s -> System.out.print(s + ", ")); // 7, 1, 4, 3, 2
    }

    private static void sortedDemo(){

        System.out.println("########## sorted view of the stream ###########");
        Stream.of(1, 4, 2, 7, 9, 10, 3)
                .sorted()
                .forEach(s -> System.out.print(s + ", "));

    }

    private static void limitDemo(){
        System.out.println("########## Applying limit ###########");
        Stream.of("one", "two", "three", "four", "five")
                .limit(2) // limiting stream for first two element only
                .forEach( System.out::println ); // one, two
    }


    private static void skipDemo(){
        System.out.println("########## Applying skip ###########");
        IntStream.of(23, 17, 8, 12, 5, 6, 9, 16, 2)
                .skip(2) // skipping first two element only
                .filter( e -> e > 10)
                .forEach(System.out::println); //12, 16
    }

    private static void peekDemo(){

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

}
