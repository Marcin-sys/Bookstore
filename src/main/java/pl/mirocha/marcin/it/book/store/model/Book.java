package pl.mirocha.marcin.it.book.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity(name = "tbook")
public class Book implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String author;
    private String isbn;
    @Column(precision = 6, scale = 2)
    private BigDecimal price;
    private int quantity;
    @ManyToOne(cascade = {}, fetch = FetchType.LAZY)
    private User creator;

    public Book(int id, String title, String author, String isbn, double price, int quantity, User creator) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = new BigDecimal(price);
        this.quantity = quantity;
        this.creator = creator;
    }

    public Book(int id, String title, String author, String isbn, double price, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = new BigDecimal(price);
        this.quantity = quantity;
    }

    public Book(int id) {
        this.id = id;
    }

    @Override
    public Book clone() {
        Book book = new Book();
        book.setId(this.id);
        book.setTitle(this.title);
        book.setAuthor(this.author);
        book.setIsbn(this.isbn);
//        book.setPrice(this.price);
        book.price = this.price;
        book.setQuantity(this.quantity);
        book.setCreator(this.creator);
        return book;
    }

    public double getPrice() {
        return this.price.doubleValue();
    }

    public void setPrice(double price) {
        this.price = new BigDecimal(price);
    }
}
