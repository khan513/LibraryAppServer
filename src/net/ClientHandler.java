package net;

import data.BookDao;
import data.UserDao;
import model.Book;
import model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream((socket.getOutputStream()));
        } catch (IOException e) {
            closeEverything(socket, in, out);
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (socket.isConnected()) {
            try {
                String request = (String) in.readObject();
                switch (request) {
                    case "Login" -> {
                        User user = (User) in.readObject();
                        if (UserDao.getAllUsers().stream().anyMatch(u -> u.getLogin().equals(user.getLogin()))) {
                            out.writeObject("Success! User was found :)");
                            out.flush();
                        } else {
                            out.writeObject("Fail! User was not found :(");
                            out.flush();
                        }
                    }
                    case "Register" -> {
                        User user = (User) in.readObject();
                        if (UserDao.getAllUsers().stream().noneMatch(u -> u.getLogin().equals(user.getLogin()))) {
                            UserDao.addUser(user);
                            out.writeObject("Success! User was registered :)");
                            out.flush();
                        } else {
                            out.writeObject("Fail! Login was taken :(");
                            out.flush();
                        }
                    }
                    case "GET BOOKS" -> {
                        out.writeObject(BookDao.getAllBooks());
                        out.flush();
                    }
                    case "POST BOOK" -> {
                        Book book = (Book) in.readObject();
                        BookDao.addBook(book);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                closeEverything(socket, in, out);
                e.printStackTrace();
            }
        }
    }

    public void closeEverything(Socket socket, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
        try {
            if (socket != null) {
                socket.close();
            }
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}