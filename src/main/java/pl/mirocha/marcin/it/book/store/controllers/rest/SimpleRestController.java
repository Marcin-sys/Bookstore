package pl.mirocha.marcin.it.book.store.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.mirocha.marcin.it.book.store.dao.IBookDAO;
import pl.mirocha.marcin.it.book.store.model.Book;
import pl.mirocha.marcin.it.book.store.model.User;

@RestController
public class SimpleRestController {
    @Autowired
    IBookDAO iBookDAO;

    @RequestMapping(path = "/rest/test1",method = RequestMethod.GET)
    public Book test1(){
        Book book = new Book(1, "Java. Podręcznik na start", "Krzysztof Krocz",
                "978-83-283-9783-5", 44.00, 10);
            return book;
    }

    @RequestMapping(path = "/rest/test3/{imie}/{nazwisko}",method = RequestMethod.GET)
    public User test3 (@PathVariable String imie,
                       @PathVariable String nazwisko,
                        @RequestParam int wiek){
        User user= new User();
        user.setName(imie);
        user.setSurname(nazwisko);
        System.out.println(wiek);

        user.setId(10);
        user.setRole(User.Role.ADMIN);
        user.setPassword("fewfgwfewfwef");
        user.setLogin(imie.toLowerCase());
        return user;
    }

    @RequestMapping(path = "/rest/test6", method = RequestMethod.POST)
    public User test6 (@RequestBody User user){
        user.setId(100);
        user.setRole(User.Role.ADMIN);
        return user;
    }
}