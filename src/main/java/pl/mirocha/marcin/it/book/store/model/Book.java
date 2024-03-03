package pl.mirocha.marcin.it.book.store.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Book implements Cloneable {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private double price;
    private int quantity;
    private User creator;

    public Book(int id, String title, String author, String isbn, double price, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public Book clone() {
        Book book = new Book();
        book.setId(this.id);
        book.setTitle(this.title);
        book.setAuthor(this.author);
        book.setIsbn(this.isbn);
        book.setPrice(this.price);
        book.setQuantity(this.quantity);
        return book;
    }
}
