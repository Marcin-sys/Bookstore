package pl.mirocha.marcin.it.book.store.validators;

import pl.mirocha.marcin.it.book.store.exceptions.BookValidationException;
import pl.mirocha.marcin.it.book.store.model.Book;

public class BookValidator {

    public static void validateTitle(String title){
        if (!title.matches("^.+$")) {
            throw new BookValidationException("Incorrect title");
        }
    }
    public static void validateAuthor(String author){
        if (!author.matches("^[A-Z][a-z]+\\ [A-Z][a-z]+([-\\ ][A-Z][a-z]+)?(,(\\ )?[A-Z][a-z]+\\ [A-Z][a-z]+([-\\ ][A-Z][a-z]+)?)*$")) {
            throw new BookValidationException("Incorrect title");
        }
    }
    public static void validateIsbn(String isbn){
        if (!isbn.matches("^(978|979)-[0-9]{2}-[0-9]{2,6}-[0-9]{1,5}-[0-9]$")) {
            throw new BookValidationException("Incorrect title");
        }
    }

    public static void validatePrice(double price){
        if (price <= 0){
            throw new BookValidationException("Price incorrect");
        }
    }

    public static void validateQuantity(int quantity){
        if (quantity < 0){
            throw new BookValidationException("Quantity cannot be negative");
        }
    }

    public static void validateBook(Book book){
        validateTitle(book.getTitle());
        validateAuthor(book.getAuthor());
        validateIsbn(book.getIsbn());
        validatePrice(book.getPrice());
        validateQuantity(book.getQuantity());
    }
}
