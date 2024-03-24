package pl.mirocha.marcin.it.book.store.services;

import pl.mirocha.marcin.it.book.store.model.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> getById(int id);

}
