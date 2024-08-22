package pl.mirocha.marcin.it.book.store.services.impl;

import org.springframework.stereotype.Service;
import pl.mirocha.marcin.it.book.store.dao.IUserDAO;
import pl.mirocha.marcin.it.book.store.model.User;
import pl.mirocha.marcin.it.book.store.services.IUserService;

import java.util.List;
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

    @Override
    public Optional<User> getByLogin(String login) {
        return this.iUserDAO.getByLogin(login);
    }

    @Override
    public List<User> getAll() {
        return this.iUserDAO.getAll();
    }

    @Override
    public void delete(int id) {
        this.iUserDAO.delete(id);
    }
}
