import java.sql.Date;

public class Book {
    private Long id;
    private String title;
    private Integer total_pages;
    private Double rating;
    private String isbn;
    private Date published_date;
    private Long publisher_id;

    public Book(Long id, String title, Integer total_pages, Double rating, String isbn, Date published_date, Long publisher_id) {
        this.id = id;
        this.title = title;
        this.total_pages = total_pages;
        this.rating = rating;
        this.isbn = isbn;
        this.published_date = published_date;
        this.publisher_id = publisher_id;
    }

    public Book(String title, Integer total_pages, Double rating, String isbn, Date published_date, Long publisher_id) {
        this.title = title;
        this.total_pages = total_pages;
        this.rating = rating;
        this.isbn = isbn;
        this.published_date = published_date;
        this.publisher_id = publisher_id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getPublished_date() {
        return published_date;
    }

    public void setPublished_date(Date published_date) {
        this.published_date = published_date;
    }

    public Long getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(Long publisher_id) {
        this.publisher_id = publisher_id;
    }
}