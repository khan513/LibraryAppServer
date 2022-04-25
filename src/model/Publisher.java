package model;

public class Publisher {
    private Long id;
    private String name;

    public Publisher() {}

    public Publisher(String name) {
        this.name = name;
    }

    public Publisher(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
}