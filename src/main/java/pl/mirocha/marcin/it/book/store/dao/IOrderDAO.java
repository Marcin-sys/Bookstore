package pl.mirocha.marcin.it.book.store.dao;

import pl.mirocha.marcin.it.book.store.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderDAO {
    Optional<Order> getById(int id);

    List<Order> getAll();


    void save(Order order);

    void update(Order order);

    void delete(int id);

    List<Order> getByUserId(int userId);
}
