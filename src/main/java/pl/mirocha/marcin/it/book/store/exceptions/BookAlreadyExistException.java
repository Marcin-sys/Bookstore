package pl.mirocha.marcin.it.book.store.exceptions;

public class BookAlreadyExistException extends RuntimeException{
    public BookAlreadyExistException(String message) {
        super(message);
    }
}
