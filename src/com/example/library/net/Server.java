package com.example.library.net;

import com.example.library.data.Database;
import com.example.library.data.LibraryDB;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server {
    private final ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            System.out.println("Server has started at " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME).substring(0, 16));
            Database libraryDB = new LibraryDB();
            libraryDB.connectToTheDatabase();
            while (!serverSocket.isClosed()) {
                System.out.println("Server is waiting for clients...");
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected at " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME).substring(0, 16));
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.start();
            }
        } catch (IOException e) {
            closeServerSocket();
            e.printStackTrace();
        }
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}