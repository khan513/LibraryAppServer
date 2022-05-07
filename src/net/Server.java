package net;

import data.LibraryDB;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Server {
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            System.out.println("Server has started at " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
            LibraryDB.connectToTheDatabase();
            while (!serverSocket.isClosed()) {
                System.out.println("Server is waiting for clients...");
                Socket socket = serverSocket.accept();
                System.out.println("A new client with the address of " + socket.getInetAddress().getHostAddress().toLowerCase(Locale.ROOT) + " has connected! :)");
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