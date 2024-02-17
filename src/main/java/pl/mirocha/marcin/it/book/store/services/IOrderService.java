package pl.mirocha.marcin.it.book.store.services;

import pl.mirocha.marcin.it.book.store.model.Order;

import java.util.List;

public interface IOrderService {
    void confirm();
    List<Order> getCurrentUserOrders();
}
