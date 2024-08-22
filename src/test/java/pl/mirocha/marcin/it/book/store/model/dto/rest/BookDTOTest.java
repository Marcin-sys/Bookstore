package pl.mirocha.marcin.it.book.store.model.dto.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.mirocha.marcin.it.book.store.TestSuite;
import pl.mirocha.marcin.it.book.store.exceptions.UserNotExistException;
import pl.mirocha.marcin.it.book.store.model.Book;
import pl.mirocha.marcin.it.book.store.model.User;

import java.util.Optional;

class BookDTOTest extends TestSuite {

    @Test
    void toBookTest() {
        BookDTO bookDTO = new BookDTO(1, "tytul", "author", "123-123-123-123",
                30.00, 10, 1);
        Mockito.when(this.userDAO.getById(1)).thenReturn(generateFakeUser());

        Book book = bookDTO.toBook(this.userDAO);

        Assertions.assertEquals(bookDTO.getTitle(), book.getTitle());
        Assertions.assertEquals(bookDTO.getAuthor(), book.getAuthor());
        Assertions.assertEquals(bookDTO.getIsbn(), book.getIsbn());
        Assertions.assertEquals(bookDTO.getPrice(), book.getPrice(), 0.001);
        Assertions.assertEquals(bookDTO.getQuantity(), book.getQuantity());
        Assertions.assertEquals(bookDTO.getCreatorId(), book.getCreator().getId());
    }
    @Test
    public void toBookWhenUserNotExistTest(){
        BookDTO bookDTO = new BookDTO(1, "tytul", "author", "123-123-123-123",
                30.00, 10, 1);
        Mockito.when(this.userDAO.getById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotExistException.class,
                ()->bookDTO.toBook(this.userDAO));
    }
}