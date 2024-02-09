package pl.mirocha.marcin.it.book.store.exceptions;

public class BookValidationException extends RuntimeException{
    public BookValidationException(String message) {
        super(message);
    }
}
