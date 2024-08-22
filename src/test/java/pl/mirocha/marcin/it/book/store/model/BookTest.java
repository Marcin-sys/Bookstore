package pl.mirocha.marcin.it.book.store.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class BookTest {

    @Test
    void testClone() {
        Book book = new Book();
        User user = new User();

        book.setId(10);
        book.setTitle("tytul");
        book.setAuthor("autor");
        book.setIsbn("123-123-123-123");
        book.setQuantity(10);
        book.setPrice(30.00);
        book.setCreator(user);

        Book clone = book.clone();

        Assertions.assertEquals(book.getId(),clone.getId());
        Assertions.assertEquals(book.getTitle(),clone.getTitle());
        Assertions.assertEquals(book.getAuthor(),clone.getAuthor());
        Assertions.assertEquals(book.getIsbn(),clone.getIsbn());
        Assertions.assertEquals(book.getQuantity(),clone.getQuantity());
        Assertions.assertEquals(book.getPrice(),clone.getPrice());
        Assertions.assertSame(book.getCreator(),clone.getCreator());
        Assertions.assertNotSame(book,clone);
    }
}