package pl.mirocha.marcin.it.book.store.services.impl;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mirocha.marcin.it.book.store.dao.IBookDAO;
import pl.mirocha.marcin.it.book.store.dao.IUserDAO;
import pl.mirocha.marcin.it.book.store.model.Book;
import pl.mirocha.marcin.it.book.store.model.Position;
import pl.mirocha.marcin.it.book.store.model.User;
import pl.mirocha.marcin.it.book.store.services.ICartService;

import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    HttpSession httpSession;

    private IBookDAO bookDAO;
    private IUserDAO userDAO;

    public CartService(IBookDAO bookDAO, IUserDAO userDAO) {
        this.bookDAO = bookDAO;
        this.userDAO = userDAO;
    }

    @Override
    public void addBook(final int bookId) {
        Optional<Book> bookBox =this.bookDAO.getById(bookId);

        if(bookBox.isEmpty()){
            return;
        }

        User user = (User) this.httpSession.getAttribute("user");
        Optional<Position> position = user.getCart().stream()
                .filter(p -> p.getBook().getId() == bookId)
                .findFirst();

        if (position.isPresent()){
            position.get().incrementQuantity();
        }else {
            Position newPosition = new Position();
            newPosition.setBook(bookBox.get());
            newPosition.setQuantity(1);

            user.getCart().add(newPosition);
        }
        this.userDAO.update(user);
    }
}
