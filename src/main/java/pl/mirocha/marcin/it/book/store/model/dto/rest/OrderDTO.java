package pl.mirocha.marcin.it.book.store.model.dto.rest;

import lombok.*;
import pl.mirocha.marcin.it.book.store.dao.IBookDAO;
import pl.mirocha.marcin.it.book.store.dao.IUserDAO;
import pl.mirocha.marcin.it.book.store.exceptions.UserNotExistException;
import pl.mirocha.marcin.it.book.store.model.Order;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {
    private int id;
    private LocalDateTime dateTime;
    private Order.Status status;
    private double total;
    private int userId;
    private final Set<PositionDTO> positions = new HashSet<>();

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.total =order.getTotal();
        this.status = order.getStatus();
        this.userId =order.getUser().getId();
        this.dateTime=order.getDateTime();
        this.positions.addAll(order.getPositions().stream().map(position -> new PositionDTO()).toList());
    }

    public Order toOrder(IUserDAO iUserDAO, IBookDAO bookDAO){
        Order order = new Order();
        order.setId(this.id);
        order.setStatus(this.status);
        order.setTotal(this.total);
        order.setDateTime(this.dateTime);

        order.setUser(iUserDAO.getById(this.userId).orElseThrow(()->new UserNotExistException()));

        order.getPositions().addAll(this.positions
                .stream().map(positionDTO -> positionDTO.toPosition(bookDAO)).toList());

        return order;
    }
}
