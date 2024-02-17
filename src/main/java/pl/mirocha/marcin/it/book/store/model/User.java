package pl.mirocha.marcin.it.book.store.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private String surname;
    private Role role;
    private final Set<Position> cart = new HashSet<>();

    public double total(){
        double sum = this.cart.stream()
                    .mapToDouble(i -> i.getBook().getPrice() * i.getQuantity())
                    .sum();
        return (int) (sum *100)/100.0;
    }
    @Override
    public User clone() {
        User user = new User();
        user.setId(this.id);
        user.setLogin(this.login);
        user.setPassword(this.password);
        user.setName((this.name));
        user.setSurname(this.surname);
        user.setRole(this.role);
        user.getCart().addAll(this.getCart().stream()
                .map(Position::clone)
                .toList());
        return user;
    }
    public enum Role{
        USER,
        ADMIN;
    }
}
