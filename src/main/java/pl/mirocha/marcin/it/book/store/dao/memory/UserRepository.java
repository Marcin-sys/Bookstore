package pl.mirocha.marcin.it.book.store.dao.memory;

import org.springframework.stereotype.Repository;
import pl.mirocha.marcin.it.book.store.dao.IUserDAO;
import pl.mirocha.marcin.it.book.store.exceptions.UserAlreadyExistException;
import pl.mirocha.marcin.it.book.store.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
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
/*        for (User user : this.users) {
            if (user.getId() == id) {
                return Optional.of(user.clone());
            }
        }
        return Optional.empty();*/
        return this.users.stream()
                .filter(user -> user.getId() == id)
                .map(User::clone)
                .findFirst();
    }

    @Override
    public Optional<User> getByLogin(String login) {
/*        for (User user : this.users) {
            if (user.getLogin().equals(login)) {
                return Optional.of(user.clone());
            }
        }
        return Optional.empty();*/
        return this.users.stream()
                .filter(user -> user.getLogin().equals(login))
                .map(User::clone)
                .findFirst();
    }

    @Override
    public List<User> getAll() {
/*        List<User> result = new ArrayList<>();
        for (User user : this.users) {
            result.add(user.clone());
        }
        return result;*/

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
 /*       Iterator<User> iterator = this.users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                break;
            }
        }*/
        this.users.stream()
                .filter(user -> user.getId() == id)
                .forEach(this.users::remove);
    }

    @Override
    public void update(final User user) {
/*        Optional<User> userBox = this.getById(user.getId());
        if (userBox.isEmpty()) {
            return;
        }
        User userFromDB = userBox.get();
        userFromDB.setName(user.getName());
        userFromDB.setSurname(user.getSurname());
        userFromDB.setLogin(user.getLogin());
        userFromDB.setPassword(user.getPassword());
        userFromDB.setRole(user.getRole());*/
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
