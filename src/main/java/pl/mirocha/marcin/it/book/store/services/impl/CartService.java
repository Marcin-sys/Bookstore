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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
        final Optional<Book> bookBox =this.bookDAO.getById(bookId);
        if(bookBox.isEmpty()){
            return;
        }
        final User user = (User) this.httpSession.getAttribute("user");
        user.getCart().stream()
                .filter(p -> p.getBook().getId() == bookId)
                .findFirst()
                .ifPresentOrElse(
                        Position::incrementQuantity,  //jesli bedzie present to robi tą linijke
                        ()->{   //jesli nie bedzie prezent to robi to ponizej
                            Position newPosition = new Position();
                            newPosition.setBook(bookBox.get());
                            newPosition.setQuantity(1);
                            user.getCart().add(newPosition);
                        }
                );

        this.userDAO.update(user);
    }
    @Override
    public void removeBook(final int bookID) {
        final Set<Position> positions = ((User) this.httpSession.getAttribute("user")).getCart();
        new HashSet<> (positions).stream()
                .filter(p->p.getBook().getId() == bookID)
                .forEach(positions::remove);
    }
}
