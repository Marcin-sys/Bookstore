package pl.mirocha.marcin.it.book.store.dao;

import pl.mirocha.marcin.it.book.store.model.Book;

import java.util.List;

public interface IBookDAO {
    Book getById(int id);

    List<Book> getAll();

    List<Book> getByPattern(String pattern);

    void save(Book book);

    void update(Book book);

    void delete(int id);
}
