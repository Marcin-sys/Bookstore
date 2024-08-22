package pl.mirocha.marcin.it.book.store;

import pl.mirocha.marcin.it.book.store.model.Book;
import pl.mirocha.marcin.it.book.store.model.User;
import pl.mirocha.marcin.it.book.store.model.dto.rest.ResponseListDTO;

public class GenericTest {
    public static void main(String[] args) {
        ResponseListDTO<Book> responseListDTO = new ResponseListDTO<>();
        responseListDTO.getElements().add(new Book());


        ResponseListDTO<User> responseListDTO2 = new ResponseListDTO<>();
        responseListDTO2.getElements().add(new User());
    }
}
