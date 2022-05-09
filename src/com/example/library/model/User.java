package com.example.library.model;

import java.io.Serializable;

public class User extends Person implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private String password;

    private Sex sex;

    public User() {
    }

    public User(String name, String surname, String login, String password, Sex sex) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
    }

    public User(Long id, String name, String surname, String login, String password, Sex sex) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
