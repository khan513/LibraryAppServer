package net;
import data.BookRepository;
import data.Dao;

public class Main {
    public static void main(String[] args) {
        Dao.connectToTheDatabase();
        BookRepository.getAllBooks().forEach(System.out::println);
    }
}