package pl.mirocha.marcin.it.book.store.exceptions;

public class UserValidationException extends RuntimeException {

    public UserValidationException(String message) {
        super(message);
    }
}
