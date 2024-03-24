package pl.mirocha.marcin.it.book.store.services.impl;

import org.springframework.stereotype.Service;
import pl.mirocha.marcin.it.book.store.dao.IUserDAO;
import pl.mirocha.marcin.it.book.store.model.User;
import pl.mirocha.marcin.it.book.store.services.IUserService;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final IUserDAO iUserDAO;

    public UserService(IUserDAO iUserDAO) {
        this.iUserDAO = iUserDAO;
    }

    @Override
    public Optional<User> getById(int id) {
        return this.iUserDAO.getById(id);
    }
}
