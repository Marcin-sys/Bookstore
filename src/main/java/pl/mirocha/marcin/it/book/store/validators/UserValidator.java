package pl.mirocha.marcin.it.book.store.validators;

import pl.mirocha.marcin.it.book.store.exceptions.UserValidationException;
import pl.mirocha.marcin.it.book.store.model.dto.RegisterUserDTO;

public class UserValidator {

    public static void validateLogin(String login){
        if(login.length() < 5) {
            throw new UserValidationException("Login too short");
        }
    }

    public static void validateName(String name){
        if(name.length() < 3 || !Character.isUpperCase(name.charAt(0))) {
            throw new UserValidationException("Name incorrect!");
        }
    }
    public static void validateSurName(String surname){
        if(surname.length() < 2 || !Character.isUpperCase(surname.charAt(0))) {
            throw new UserValidationException("Surname incorrect!");
        }
    }
    public static void validatePassword(String password){
        if(password.length() < 5) {
            throw new UserValidationException("Password too short");
        }
    }

    public static void validatePasswordEquality(String password,String password2){
        if(!password.equals(password2)) {
            throw new UserValidationException("Password is not equals");
        }
    }
    public static void validateUserDTO(RegisterUserDTO userDTO){
        UserValidator.validateName(userDTO.getName());
        UserValidator.validateSurName(userDTO.getSurname());
        UserValidator.validateLogin(userDTO.getLogin());
        UserValidator.validatePassword(userDTO.getPassword());
        UserValidator.validatePasswordEquality(userDTO.getPassword(),userDTO.getPassword2());
    }
}
