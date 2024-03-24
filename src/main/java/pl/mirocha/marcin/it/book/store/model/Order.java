package pl.mirocha.marcin.it.book.store.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity(name = "torder")
public class Order implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime dateTime;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(precision = 6, scale = 2)
    private BigDecimal total;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch = FetchType.EAGER)
    private User user;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private final Set<Position> positions = new HashSet<>();


    public Order(int id, LocalDateTime dateTime, Status status, double total, User user) {
        this.id = id;
        this.dateTime = dateTime;
        this.status = status;
        this.total = new BigDecimal(total);
        this.user = user;
    }

    public Order(int id) {
        this.id = id;
    }

    public String getFormattedDate(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return this.dateTime.format(dateTimeFormatter);
    }


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
//        order.setTotal(this.total);
        order.total = this.total;
        order.setStatus(this.status);
        order.setUser(this.user.clone());
        order.getPositions().addAll(this.positions.stream().map(Position::clone).toList());
        return order;
    }

    public double getTotal() {
        return this.total.doubleValue();
    }

    public void setTotal(double total) {
        this.total = new BigDecimal(total);
    }
}
