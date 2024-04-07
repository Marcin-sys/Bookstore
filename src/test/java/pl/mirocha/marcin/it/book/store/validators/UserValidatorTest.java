package pl.mirocha.marcin.it.book.store.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.mirocha.marcin.it.book.store.exceptions.UserValidationException;
import pl.mirocha.marcin.it.book.store.model.dto.RegisterUserDTO;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {

    @Test
    void validateCorrectLogin() {
        String login = "janusz";

        UserValidator.validateLogin(login);
    }
    @Test
    void validateIncorrectLogin() {
        String login = "janusz%";

        Assertions.assertThrows(UserValidationException.class,
                ()->UserValidator.validateLogin(login));
    }

    @Test
    void validateCorrectName() {
        String name = "Janusz";

        UserValidator.validateName(name);
    }

    @Test
    void validateToShortName() {
        String name = "J";

        Assertions.assertThrows(UserValidationException.class,
                ()->UserValidator.validateName(name));
    }
    @Test
    void validateStartLowerCaseNameTest() {
        String name = "janusz";

        Assertions.assertThrows(UserValidationException.class,
                ()->UserValidator.validateName(name));
    }

    @Test
    void validateCorrectSurName() {
        String surname = "Kowalski";

        UserValidator.validateSurName(surname);
    }

    @Test
    void validateTwoSurName() {
        String surname = "Kowalski-Malinowski";

        UserValidator.validateSurName(surname);
    }

    @Test
    void validateIncorrectLowerCaseSurName() {
        String surname = "kowalski-Malinowski";

        Assertions.assertThrows(UserValidationException.class,
                ()->UserValidator.validateSurName(surname));
    }

    @Test
    void validatePasswordTest() {
        String password = "Kowal12";

        UserValidator.validatePassword(password);
    }
    @Test
    void validateCorrectPasswordTest() {
        String password = "12kowal";

        UserValidator.validatePassword(password);
    }
    @Test
    void validateCorrectBetweenNumberPasswordTest() {
        String password = "ko2wal";

        UserValidator.validatePassword(password);
    }

    @Test
    void validateIncorrectWithoutNumberPasswordTest() {
        String password = "kowal";

        Assertions.assertThrows(UserValidationException.class,
                ()->UserValidator.validatePassword(password));
    }

    @Test
    void validateIncorrectTooShortPasswordTest() {
        String password = "kow";

        Assertions.assertThrows(UserValidationException.class,
                ()->UserValidator.validatePassword(password));
    }

    @Test
    void validatePasswordEquality() {
        String password = "kow";
        String password2 = "kow";

        UserValidator.validatePasswordEquality(password,password2);
    }

    @Test
    void validateIncorrectPasswordEquality() {
        String password = "kow";
        String password2 = "ko";

        Assertions.assertThrows(UserValidationException.class,
                ()->UserValidator.validatePasswordEquality(password,password2));
    }

    @Test
    void validateUserDTO() {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        registerUserDTO.setLogin("janusz");
        registerUserDTO.setName("Janusz");
        registerUserDTO.setSurname("Kowalski");
        registerUserDTO.setPassword("haslo1");
        registerUserDTO.setPassword2("haslo1");

        UserValidator.validateUserDTO(registerUserDTO);
    }
}