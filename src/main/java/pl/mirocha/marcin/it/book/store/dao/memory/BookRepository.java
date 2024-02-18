package pl.mirocha.marcin.it.book.store.dao.memory;

import org.springframework.stereotype.Repository;
import pl.mirocha.marcin.it.book.store.dao.IBookDAO;
import pl.mirocha.marcin.it.book.store.exceptions.BookAlreadyExistException;
import pl.mirocha.marcin.it.book.store.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository implements IBookDAO {

    private final List<Book> books = new ArrayList<>();
    private final IdSequence idSequence;

    public BookRepository(IdSequence bookIdSequence) {
        this.idSequence = bookIdSequence;
        this.books.add(new Book(bookIdSequence.getId(), "Java. Podręcznik na start", "Krzysztof Krocz",
                "978-83-283-9783-5", 44.85, 10));
        this.books.add(new Book(bookIdSequence.getId(), "Java. Kompendium programisty. Wydanie XII",
                "Herbert Schildt",
                "978-83-832-2156-4", 129.35, 10));
        this.books.add(new Book(bookIdSequence.getId(), "Java. Rusz głową! Wydanie III",
                "Kathy Sierra, Bert Bates, Trisha Gee",
                "978-83-283-9984-6", 96.85, 10));
        this.books.add(new Book(bookIdSequence.getId(), "Java. Przewodnik doświadczonego programisty. Wydanie III",
                "Cay S. Horstmann", "978-83-289-0141-4", 57.84, 10));
    }

    @Override
    public Optional<Book> getById(final int id) {
/*        for (Book book : this.books) {
            if (book.getId() == id) {
                return Optional.of(book.clone());
            }
        }
        return Optional.empty();*/

        return this.books.stream()
                .filter(book -> book.getId() == id)
                .map(book -> book.clone())
                .findFirst();
    }

    @Override
    public List<Book> getAll() {
/*        List<Book> result = new ArrayList<>();
        for (Book book : this.books) {
            result.add(book.clone());
        }
        return result;*/

        return this.books.stream()
                .map(book -> book.clone())
                .toList();
    }

    @Override
    public List<Book> getByPattern(final String pattern) {
/*        List<Book> result = new ArrayList<>();
        for (Book book : this.books) {
            if (book.getTitle().toLowerCase().contains(pattern.toLowerCase())
                    || book.getAuthor().toLowerCase().contains(pattern.toLowerCase())) {
                result.add(book.clone());
            }
        }
        return result;*/

        return this.books.stream()
                .filter(book -> matchToPattern(book, pattern))
                .map(book -> book.clone())
                .toList();

    }
    private boolean matchToPattern(Book book, String pattern) {
        return book.getTitle().toLowerCase().contains(pattern.toLowerCase())
                || book.getAuthor().toLowerCase().contains(pattern.toLowerCase());
    }

    @Override
    public void save(final Book book) {
/*        for (Book bookFromDb : this.books) {
            if (bookFromDb.getIsbn().equals(book.getIsbn())) {
                throw new BookAlreadyExistException("Book with isbn: " + book.getIsbn()
                        + " already exist");
            }
        }
        book.setId(bookIdSequence.getId());
        this.books.add(book);*/

        Optional<Book> bookFromDB = this.books.stream()
                .filter(book1 -> book1.getIsbn().equals(book.getIsbn()))
                .findFirst();
        if (bookFromDB.isPresent()){
            throw new BookAlreadyExistException("Book with isbn: " + book.getIsbn()
                    + " already exist");
        }
        book.setId(idSequence.getId());
        this.books.add(book);
    }

    @Override
    public void update(final Book book) {
/*        for (Book bookFromDb : this.books) {
            if (bookFromDb.getId() == book.getId()) {
                bookFromDb.setTitle(book.getTitle());
                bookFromDb.setAuthor(book.getAuthor());
                bookFromDb.setIsbn(book.getIsbn());
                bookFromDb.setPrice(book.getPrice());
                bookFromDb.setQuantity(book.getQuantity());
                return;
            }
        }*/
        this.books.stream()
                .filter(book1 -> book1.getId() == book.getId())
                .forEach(book1 -> {
                    book1.setTitle(book.getTitle());
                    book1.setAuthor(book.getAuthor());
                    book1.setIsbn(book.getIsbn());
                    book1.setPrice(book.getPrice());
                    book1.setQuantity(book.getQuantity());
                });
    }

    @Override
    public void delete(final int id) {
/*        Iterator<Book> iterator = this.books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId() == id) {
                iterator.remove();
                break;
            }
        }*/
        this.books.stream()
                .filter(book -> book.getId() == id)
                .forEach(book -> this.books.remove(book));
    }
}
