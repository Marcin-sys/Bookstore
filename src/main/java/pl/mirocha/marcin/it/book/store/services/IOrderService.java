package pl.mirocha.marcin.it.book.store.services;

import pl.mirocha.marcin.it.book.store.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    void confirm();
    List<Order> getCurrentUserOrders();

    Optional<Order> getById(int id);
}
