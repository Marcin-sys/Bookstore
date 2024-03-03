package pl.mirocha.marcin.it.book.store.services.impl;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mirocha.marcin.it.book.store.dao.IBookDAO;
import pl.mirocha.marcin.it.book.store.model.Book;
import pl.mirocha.marcin.it.book.store.model.User;
import pl.mirocha.marcin.it.book.store.services.IBookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    HttpSession httpSession;
    private final IBookDAO bookDAO;


    public BookService(IBookDAO iBookDAO) {
        this.bookDAO = iBookDAO;
    }

    @Override
    public void save(Book book) {
        book.setCreator((User) this.httpSession.getAttribute("user"));
        this.bookDAO.save(book);
    }

    @Override
    public Optional<Book> getById(int id) {
        return this.bookDAO.getById(id);
    }

    @Override
    public void update(int id, Book book) {
        book.setId(id); //nie bylo w form ustawionego id
        this.bookDAO.update(book);
    }

    @Override
    public List<Book> getByPattern(String pattern) {
        return this.bookDAO.getByPattern(pattern);
    }

    @Override
    public List<Book> getAll() {
        return this.bookDAO.getAll();
    }
}
