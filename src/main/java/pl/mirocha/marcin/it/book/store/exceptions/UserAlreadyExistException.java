package pl.mirocha.marcin.it.book.store.exceptions;

public class UserAlreadyExistException extends RuntimeException{

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
