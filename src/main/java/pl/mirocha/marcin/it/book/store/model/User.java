package pl.mirocha.marcin.it.book.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String role;

    @Override
    public User clone() {
        User user = new User();
        user.setId(this.id);
        user.setLogin(this.login);
        user.setPassword(this.password);
        user.setName((this.name));
        user.setSurname(this.surname);
        user.setRole(this.role);
        return user;
    }
}
