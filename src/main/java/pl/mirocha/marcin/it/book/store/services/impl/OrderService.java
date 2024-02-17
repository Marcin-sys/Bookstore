package pl.mirocha.marcin.it.book.store.services.impl;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mirocha.marcin.it.book.store.dao.IBookDAO;
import pl.mirocha.marcin.it.book.store.dao.IOrderDAO;
import pl.mirocha.marcin.it.book.store.dao.IUserDAO;
import pl.mirocha.marcin.it.book.store.exceptions.InvalidCartException;
import pl.mirocha.marcin.it.book.store.model.Book;
import pl.mirocha.marcin.it.book.store.model.Order;
import pl.mirocha.marcin.it.book.store.model.User;
import pl.mirocha.marcin.it.book.store.services.IOrderService;

import java.util.List;
import java.util.Optional;
@Service
public class OrderService implements IOrderService {

    @Autowired
    HttpSession httpSession;

    private final IBookDAO bookDAO;
    private final IOrderDAO orderDAO;
    private final IUserDAO userDAO;

    public OrderService(IBookDAO bookDAO, IOrderDAO orderDAO, IUserDAO userDAO) {
        this.bookDAO = bookDAO;
        this.orderDAO = orderDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void confirm() {
        final User user = (User) this.httpSession.getAttribute("user");

        long incorrectBooks = user.getCart().stream()
                .filter(position -> {
                    Optional<Book> bookFromDB = this.bookDAO.getById(position.getBook().getId());
                    return bookFromDB.isEmpty() || bookFromDB.get().getQuantity() < position.getQuantity();
                })
                .peek(position -> user.getCart().remove(position))
                .count();
        if (incorrectBooks > 0) {
            throw new InvalidCartException();
        }

        user.getCart().forEach(p -> {
            Optional<Book> bookFromDB = this.bookDAO.getById(p.getBook().getId());
            bookFromDB.get().setQuantity(bookFromDB.get().getQuantity() - p.getQuantity());
            this.bookDAO.update(bookFromDB.get());
            p.setBook(bookFromDB.get());
        });

        Order order = new Order();
        order.setUser(user);
        order.setTotal(user.total());
        order.setStatus(Order.Status.NEW);
        order.getPositions().addAll(user.getCart());

        this.orderDAO.save(order);  //save order

          //czyszczenie carta usera
        user.getCart().clear();
        this.userDAO.update(user);
    }

    @Override
    public List<Order> getCurrentUserOrders() {
        User user = (User) this.httpSession.getAttribute("user");
        return this.orderDAO.getByUserId(user.getId());
    }
}