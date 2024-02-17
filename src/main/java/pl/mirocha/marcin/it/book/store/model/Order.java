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
public class Order implements Cloneable{
    private int id;
    // private data;
    private Status status;
    private double total;
    private User user;
    private final Set<Position> positions = new HashSet<>();

    public enum Status{
        NEW,
        PAID,
        SENT,
        DONE
    }

    @Override
    public Order clone() {
        Order order = new Order();
        order.setId(this.id);
        order.setTotal(this.total);
        order.setStatus(this.status);
        order.setUser(this.user.clone());
        order.getPositions().addAll(this.positions.stream().map(Position::clone).toList());
        return order;
    }
}
