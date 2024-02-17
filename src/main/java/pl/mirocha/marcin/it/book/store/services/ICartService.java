package pl.mirocha.marcin.it.book.store.services;

public interface ICartService {
    void addBook(int bookId);
    void removeBook(int bookID);
}
