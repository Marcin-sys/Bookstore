package pl.mirocha.marcin.it.book.store.dao;

import pl.mirocha.marcin.it.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public class BookDAOStub implements IBookDAO{
    @Override
    public Optional<Book> getById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public List<Book> getByPattern(String pattern) {
        return null;
    }

    @Override
    public void save(Book book) {

    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(int id) {

    }
}
