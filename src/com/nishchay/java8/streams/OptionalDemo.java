package com.nishchay.java8.streams;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

public class OptionalDemo {

    public static void main(String[] args) {

        optionalDemo();
        emptyOptionalDemo();

    }


    private static void optionalDemo() {
        // Exception in thread "main" java.util.NoSuchElementException: No value present
        Optional<String> anyElement = Collections.EMPTY_LIST.stream().findAny();
        System.out.println("Find any - " + anyElement); // Optional.empty
        System.out.println(Optional.empty().equals(anyElement)); // true

        Optional<String> nameGt4 =
                Stream.of("java", "perl")
                        .filter(e -> e.length() > 4)
                        .findFirst();

        System.out.println("nameGt4 - " + nameGt4); // Optional.empty
    }

    private static void emptyOptionalDemo() {
//        Optional<String> nullOptional = Optional.of(null); //java.lang.NullPointerException
        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional.isPresent());
        System.out.println(Optional.empty().equals(emptyOptional));
    }

}