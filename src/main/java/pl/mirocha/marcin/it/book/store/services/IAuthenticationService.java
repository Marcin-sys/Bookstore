package pl.mirocha.marcin.it.book.store.services;

import pl.mirocha.marcin.it.book.store.model.dto.RegisterUserDTO;

public interface IAuthenticationService {

    void login(String login, String password);
    void logout();
    void register(RegisterUserDTO userDTO);
}
