package com.nishchay.java8.streams.flatmap;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapDemo {


    public static void main(String[] args) {

        flatMapEx();

        List<Book> books = Book.getListOfBook();

        mapAndCollectToList(books);
        flatMapDemoLowerToUpperObject();
        flatMapDemoUpperToLowerObject(books);
        flatMapEx_Object();

    }

    private static void flatMapEx() {
        // Find unique chars from list of words
        String[] arrOfWords = {"Goodbye", "World"};
        // array[] to stream
        Stream<String> streamOfWords = Arrays.stream(arrOfWords);

/*
        wrong usage of map - using map in place of flatMap
        List<String> uniqueChars =
                streamOfWords
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
*/


        List<String> uniqueChars = streamOfWords
                .map(word -> word.split(""))
                //.flatMap(e -> Arrays.stream(e))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        System.out.println("uniqueChars = " + uniqueChars);
    }


    private static void mapAndCollectToList(List<Book> books) {

        System.out.println(" ----------------map() & Collectors.toList() demo ----------------");
        List<String> authors =
                books.stream()
                        .map(Book::getAuthorName)
                        .sorted()
                        .collect(Collectors.toList());

        System.out.println("authors = " + authors);
    }

    private static void flatMapDemoLowerToUpperObject() {

        System.out.println(" ---------------- LowerToUpperObject ----------------");
        List<String> writers = Arrays.asList("Spider Man", "Iron Man", "Super Man", "Wonder Women");
        // Mapping : LowerToUpperObject
        List<Book> books = writers.stream().map(writer -> new Book(1001, "Book 1", writer, 100))
                .collect(Collectors.toList());

        books.forEach(System.out::println);
    }



    private static void flatMapDemoUpperToLowerObject(List<Book> books) {

        System.out.println(" ---------------- UpperToLowerObject ----------------");
        // Mapping : UpperToLowerObject
        int totalPage = books.stream()
                //.mapToInt(e -> e.getPageCount())
                .mapToInt(Book::getPageCount)
                .sum();

        System.out.println("totalPage = " + totalPage);
    }

    private static void flatMapEx_Object() {

        Author author1 = new Author();
        author1.setName("Andrew S. Tanenbaum");
        author1.setCountry(new Country("US"));
        author1.setBooks(Arrays.asList(
                new Book(2021, "Computer Networks", "Andrew S. Tanenbaum", 700),
                new Book(2022, "Modern Operating Systems", "Andrew S. Tanenbaum", 580),
                new Book(2023, "Structured Computer Organization", "Andrew S. Tanenbaum", 400),
                new Book(2024, "Data Structure", "Andrew S. Tanenbaum", 220)
        ));

        Author author2 = new Author();
        author2.setName("E. Balagurusamy");
        author2.setCountry(new Country("India"));
        author2.setBooks(Arrays.asList(
                new Book(2051, "Object-oriented Programming with C++", "E. Balagurusamy", 400),
                new Book(2052, "Programming in ANSI C", "E. Balagurusamy", 580),
                new Book(2053, "Programming in C#: A Primer", "E. Balagurusamy", 300)
        ));

        List<Author> autherList = new ArrayList<>();
        autherList.add(author1);
        autherList.add(author2);


        List<Book> books =
                autherList.stream()
                        .map(e -> e.getBooks())
                        .flatMap(e -> e.stream())
                        .distinct()
                        .collect(Collectors.toList());


        System.out.println("------------------ books ----------------------------");
        books.forEach(System.out::println);


        Optional<Integer> minPage =
                autherList.stream()
                        .map(e -> e.getBooks())
                        .flatMap(e -> e.stream())
                        .map(e -> e.getPageCount())
                        .min(Comparator.naturalOrder());

        System.out.println("minPage = " + minPage.get());

        Optional<Book> smallestBook =
                autherList.stream()
                        .map(e -> e.getBooks())
                        .flatMap(e -> e.stream())
                        .min(Comparator.comparing(Book::getPageCount));

        System.out.println("smallestBook = " + smallestBook.get());

    }

}