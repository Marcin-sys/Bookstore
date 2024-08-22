package pl.mirocha.marcin.it.book.store.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.mirocha.marcin.it.book.store.exceptions.BookValidationException;
import pl.mirocha.marcin.it.book.store.model.Book;
import pl.mirocha.marcin.it.book.store.model.User;

class BookValidatorTest {

    @Test
    void validateCorrectTitle() {
        String title = "Tytul";

        BookValidator.validateTitle(title);
    }
    @Test
    void validateIncorrectTitle() {
        String title = "";

        Assertions.assertThrows(BookValidationException.class,
                ()->BookValidator.validateTitle(title));
    }


    @Test
    void validateSimpleAuthorTest() {
        String author = "Janusz Kowalski";

        BookValidator.validateAuthor(author);
    }

    @Test
    void validateTwoSurnamesAuthorTest() {
        String author = "Janusz Kowalski-Malinowski";

        BookValidator.validateAuthor(author);
    }

    @Test
    void validateMultipleAuthorTest() {
        String author = "Janusz Kowalski, Janusz Malinowski";

        BookValidator.validateAuthor(author);
    }

    @Test
    void validateMultipleAuthorsWhenOneHaveTwoSurnamesTest() {
        String author = "Janusz Kowalski-Malinowski, Janusz Malinowski";

        BookValidator.validateAuthor(author);
    }

    @Test
    void validateLowerCaseNameTest() {
        String author = "janusz Kowalski-Malinowski, Janusz Malinowski";

        Assertions.assertThrows(BookValidationException.class,
                ()->BookValidator.validateAuthor(author));
    }

    @Test
    void validateLowerCaseSurNameTest() {
        String author = "Janusz kowalski-Malinowski, Janusz Malinowski";

        Assertions.assertThrows(BookValidationException.class,
                ()->BookValidator.validateAuthor(author));
    }

    @Test
    void validateToShortNameTest() {
        String author = "J Kowalski";

        Assertions.assertThrows(BookValidationException.class,
                ()->BookValidator.validateAuthor(author));
    }

    @Test
    void validateCorrectIsbnTest() {
        String isbn = "978-83-283-9783-5";

        BookValidator.validateIsbn(isbn);
    }

    @Test
    void validateIncorrectIsbnTest() {
        String isbn = "000-83-283-9783-5";

        Assertions.assertThrows(BookValidationException.class,
                ()->BookValidator.validateIsbn(isbn));
    }

    @Test
    void validateCorrectPriceTest() {
        double price = 20.99;

        BookValidator.validatePrice(price);
    }

    @Test
    void validateIncorrectPriceTest() {
        double price = -3.00;

        Assertions.assertThrows(BookValidationException.class,
                ()->BookValidator.validatePrice(price));
    }

    @Test
    void validateCorrectQuantityTest() {
        int quantity = 10;

        BookValidator.validateQuantity(quantity);
    }

    @Test
    void validateNegativeQuantityTest() {
        int quantity = -1;

        Assertions.assertThrows(BookValidationException.class,
                ()->BookValidator.validateQuantity(quantity));
    }
}