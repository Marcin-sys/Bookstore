package pl.mirocha.marcin.it.book.store.dao.memory;

import org.springframework.stereotype.Repository;
import pl.mirocha.marcin.it.book.store.dao.IUserDAO;
import pl.mirocha.marcin.it.book.store.exceptions.UserAlreadyExistException;
import pl.mirocha.marcin.it.book.store.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserRepository implements IUserDAO {

    private final List<User> users = new ArrayList<>();

    private final IdSequence idSequence;

    public UserRepository(IdSequence idSequence) {
        this.users.add(new User(idSequence.getId(), "admin", "0192023a7bbd73250516f069df18b500",
                "Pan", "administrator", User.Role.ADMIN));
        this.users.add(new User(idSequence.getId(), "janusz", "1e6f2ac43951a6721d3d26a379cc7f8b",
                "Janusz", "Kowalski", User.Role.USER));
        this.idSequence = idSequence;
    }


    @Override
    public Optional<User> getById(final int id) {
        return this.users.stream()
                .filter(user -> user.getId() == id)
                .map(User::clone)
                .findFirst();
    }

    @Override
    public Optional<User> getByLogin(String login) {

        return this.users.stream()
                .filter(user -> user.getLogin().equals(login))
                .map(User::clone)
                .findFirst();
    }

    @Override
    public List<User> getAll() {

        return this.users.stream()
                .map(User::clone)
                .toList();
    }

    @Override
    public void save(User user) {
        if(this.getByLogin(user.getLogin()).isPresent()) {
            throw new UserAlreadyExistException(
                    "User with login: " + user.getLogin() + " already exist");
        }
        user.setId(this.idSequence.getId());
        this.users.add(user);
    }


    @Override
    public void delete(final int id) {

        this.users.stream()
                .filter(user -> user.getId() == id)
                .forEach(this.users::remove);
    }

    @Override
    public void update(final User user) {

        this.users.stream()
                .filter(u -> u.getId() == user.getId())
                .forEach(u -> {
                    u.setName(user.getName());
                    u.setSurname(user.getSurname());
                    u.setLogin(user.getLogin());
                    if (user.getPassword() != null) {
                        u.setPassword(user.getPassword());
                    }
                    u.setRole(user.getRole());
                    u.getCart().clear();
                    u.getCart().addAll(user.getCart());
                });
    }
}
