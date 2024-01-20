package pl.mirocha.marcin.it.book.store.dao.memory;

import org.springframework.stereotype.Component;
import pl.mirocha.marcin.it.book.store.dao.IUserDAO;
import pl.mirocha.marcin.it.book.store.exceptions.UserAlreadyExistException;
import pl.mirocha.marcin.it.book.store.model.Book;
import pl.mirocha.marcin.it.book.store.model.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UserRepository implements IUserDAO {

    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        this.users.add(new User(1,"admin","0192023a7bbd73250516f069df18b500",
                "Pan","administrator","ADMIN"));
        this.users.add(new User(2,"janusz","1e6f2ac43951a6721d3d26a379cc7f8b",
                "Janusz","Kowalski","USER"));
    }

    @Override
    public User getById(int id) {
        for (User user: this.users){
            if (user.getId() == id ){
                return user.clone();
            }
        }
        return null;
    }

    @Override
    public User getByLogin(String login) {
        for (User user: this.users){
            if (user.getLogin().equals(login)){
                return user.clone();
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        for (User user: this.users){
            result.add(user.clone());
        }
        return result;
    }

    @Override
    public void save(User user) {
        User userById = this.getById(user.getId());
        User userByLogin = this.getByLogin(user.getLogin());
        if(userById == null && userByLogin == null){
            this.users.add(user);
        }else {
            throw new UserAlreadyExistException("User with id and login already Exist");
        }
    }


    @Override
    public void delete(int id) {
        Iterator<User> iterator = this.users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            if (user.getId() == id){
                iterator.remove();
                break;
            }
        }
    }

    @Override
    public void update(User user) {
        User userFromDB = this.getById(user.getId());
        if (userFromDB == null){
            return;
        }
        userFromDB.setName(user.getName());
        userFromDB.setSurname(user.getSurname());
        userFromDB.setLogin(user.getLogin());
        userFromDB.setPassword(user.getPassword());
        userFromDB.setRole(user.getRole());
    }
}
