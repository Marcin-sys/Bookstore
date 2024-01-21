package pl.mirocha.marcin.it.book.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book implements Cloneable {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private double price;
    private int quantity;


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
