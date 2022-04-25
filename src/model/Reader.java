package model;

public class Reader {
    private Long id;
    private String login;
    private String password;

    public Reader() {}

    public Reader(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Reader(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Long getId() {
        return id;
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
}