package pl.mirocha.marcin.it.book.store.services.impl;

import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mirocha.marcin.it.book.store.TestSuite;
import pl.mirocha.marcin.it.book.store.model.Book;
import pl.mirocha.marcin.it.book.store.model.User;
import pl.mirocha.marcin.it.book.store.services.IBookService;

import java.util.Optional;

class BookServiceTest extends TestSuite {

    @Autowired
    HttpSession httpSession;
    @Autowired
    IBookService bookService;
    @Test
    void saveBookTest() {
        Book book = new Book();
        book.setTitle("Adwefewf");
        book.setAuthor("Efewfewfwe");
        book.setIsbn("432-431-431-431");
        book.setQuantity(12);
        book.setPrice(50.00);
        book.setId(0);

        Optional<User> user= generateFakeUser();

        this.httpSession.setAttribute("user",user.get());

        this.bookService.save(book);

        Assertions.assertSame(user.get(),book.getCreator());
        Mockito.verify(this.bookDAO,Mockito.times(1)).save(book);

    }
    @Test
    void updateBookTest() {
        int updateBookId  =7;
        Book book = new Book();
        book.setTitle("Adwefewf");
        book.setAuthor("Efewfewfwe");
        book.setIsbn("432-431-431-431");
        book.setQuantity(12);
        book.setPrice(50.00);
        book.setCreator(null);
        book.setId(0);

        Optional<User> user= generateFakeUser();

        this.httpSession.setAttribute("user",user.get());

        this.bookService.update(updateBookId,book);

        Assertions.assertSame(user.get(),book.getCreator());
        Assertions.assertEquals(updateBookId,book.getId());
        Mockito.verify(this.bookDAO,Mockito.times(1)).update(book); //TODO
    }

    @Test
    void testUpdate() {
    }

    @Test
    void getByPattern() {
    }

    @Test
    void getAll() {
    }

    @Test
    void testSave() {
    }

}