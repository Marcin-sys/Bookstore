package pl.mirocha.marcin.it.book.store.validators;

import pl.mirocha.marcin.it.book.store.exceptions.BookValidationException;
import pl.mirocha.marcin.it.book.store.exceptions.UserValidationException;
import pl.mirocha.marcin.it.book.store.model.Book;

public class BookValidator {

    public static void validateTitle(String title){
        if (!title.matches("^.+$")) {  //TODO regex
            throw new UserValidationException("Incorrect title");
        }
    }
    public static void validateAuthor(String author){
        if (!author.matches("^.+$")) {  //TODO regex
            throw new UserValidationException("Incorrect title");
        }
    }
    public static void validateIsbn(String isbn){
        if (!isbn.matches("^.+$")) {  //TODO regex
            throw new UserValidationException("Incorrect title");
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
