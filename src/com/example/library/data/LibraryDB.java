package com.example.library.data;


import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class LibraryDB implements Database {
    static Connection connection;

    public void connectToTheDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            BufferedReader reader = new BufferedReader(new FileReader("/home/khan/IdeaProjects/LibraryAppServer/db_config"));
            String line, url, user, password;
            line = reader.readLine();
            url = line.substring(line.indexOf(" ") + 1);
            line = reader.readLine();
            user = line.substring(line.indexOf(" ") + 1);
            line = reader.readLine();
            password = line.substring(line.indexOf(" ") + 1);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database successfully! :)");
        } catch (Exception e) {
            System.out.println("Could not connect to the database :(");
            e.printStackTrace();
        }
    }
}