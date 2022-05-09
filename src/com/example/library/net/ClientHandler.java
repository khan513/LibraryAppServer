package com.example.library.net;

import com.example.library.data.AuthorDao;
import com.example.library.data.BookDao;
import com.example.library.data.UserDao;
import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class ClientHandler extends Thread {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Request request;
            while ((request = (Request) in.readObject()) != null) {
                System.out.println("Request [" +
                        "Type: " + request.getType() +
                        "\tDescription: " + request.getDescription() +
                        "\tTime: " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME).substring(0, 16)
                        + "]"
                );
                switch (request.getType()) {
                    case GET -> {
                        if (request.getDescription().equals("LOGIN")) {
                            User user = UserDao.login(request.getArgs().get(0), request.getArgs().get(1));
                            out.writeObject(user);
                        }
                        if (request.getDescription().equals("USER'S BOOKS")) {
                            List<Book> books = BookDao.getBooksByReadId(Long.valueOf(request.getArgs().get(0)));
                            out.writeObject(books);
                        }
                        if (request.getDescription().equals("ALL THE BOOKS")) {
                            List<Book> books = BookDao.getAllBooks();
                            out.writeObject(books);
                        }
                        if (request.getDescription().equals("ALL THE AUTHORS")) {
                            List<Author> authors = AuthorDao.getAllAuthors();
                            out.writeObject(authors);
                        }
                    }
                    case POST -> {
                        if (request.getDescription().equals("REGISTER")) {
                            UserDao.addUser((User) request.getObject());
                        }
                        if (request.getDescription().equals("BOOK")) {
                            BookDao.addBook((Book) request.getObject());
                        }
                        if (request.getDescription().equals("AUTHOR")) {
                            AuthorDao.addAuthor((Author) request.getObject());
                        }
                    }
                    case PUT -> {
                        if (request.getDescription().equals("USER")) {
                            UserDao.editUser(Long.valueOf(request.getArgs().get(0)), (User) request.getObject());
                        }
                        if (request.getDescription().equals("BOOK")) {
                            BookDao.editBook(Long.valueOf(request.getArgs().get(0)), (Book) request.getObject());
                        }
                        if (request.getDescription().equals("AUTHOR")) {
                            AuthorDao.editAuthor(Long.valueOf(request.getArgs().get(0)), (Author) request.getObject());
                        }
                    }
                    case DELETE -> {
                        if (request.getDescription().equals("USER")) {
                            UserDao.deleteUserById(Long.valueOf(request.getArgs().get(0)));
                        }
                        if (request.getDescription().equals("BOOK")) {
                            BookDao.deleteBookById(Long.valueOf(request.getArgs().get(0)));
                        }
                        if (request.getDescription().equals("AUTHOR")) {
                            AuthorDao.deleteAuthorById(Long.valueOf(request.getArgs().get(0)));
                        }
                    }
                }
            }
        } catch (Exception e) {
            closeEverything(socket, in, out);
            e.printStackTrace();
        }

    }

    public void closeEverything(Socket socket, ObjectInputStream in, ObjectOutputStream out) {
        try {
            if (socket != null) {
                socket.close();
            }
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}