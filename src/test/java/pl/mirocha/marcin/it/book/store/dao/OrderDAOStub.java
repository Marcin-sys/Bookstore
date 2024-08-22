package pl.mirocha.marcin.it.book.store.dao;

import pl.mirocha.marcin.it.book.store.model.Order;

import java.util.List;
import java.util.Optional;

public class OrderDAOStub implements IOrderDAO{
    @Override
    public Optional<Order> getById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public void save(Order order) {

    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Order> getByUserId(int userId) {
        return null;
    }
}
