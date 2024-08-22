package pl.mirocha.marcin.it.book.store.services.impl;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mirocha.marcin.it.book.store.TestSuite;
import pl.mirocha.marcin.it.book.store.exceptions.InvalidCartException;
import pl.mirocha.marcin.it.book.store.model.Book;
import pl.mirocha.marcin.it.book.store.model.Position;
import pl.mirocha.marcin.it.book.store.model.User;
import pl.mirocha.marcin.it.book.store.services.IOrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest  extends TestSuite {

    @Autowired
    HttpSession httpSession;

    @Autowired
    IOrderService orderService;
    @Test
    @Disabled
    void confirmOrderWithNoExistingBookTest() {
        Optional<User> user = generateFakeUser();
        user.get().getCart().addAll(generateFakePosition());
        this.httpSession.setAttribute("user",user.get());

        Mockito.when(this.bookDAO.getById(1)).thenReturn(Optional.empty());
        Mockito.when(this.bookDAO.getById(2)).thenReturn(Optional.of(new Book( 2,
                "Java. Podręcznik na start", "Krzysztof Krocz",
                "978-83-283-9783-5", 44.85,5,new User())));
        int expcetedCartSize = 1;

        Assertions.assertThrows(InvalidCartException.class,
                ()->  this.orderService.confirm());

        Assertions.assertEquals(expcetedCartSize,user.get().getCart().size());

    }

    @Test
    @Disabled
    public void successOrderConfirmation(){
        Optional<User> user = generateFakeUser();
        user.get().getCart().addAll(generateFakePosition());
        this.httpSession.setAttribute("user",user.get());

        Mockito.when(this.bookDAO.getById(1)).thenReturn(Optional.of(new Book( 1,
                "Java. Podręcznik na start", "Krzysztof Krocz",
                "978-83-283-9783-5", 44.85,5,new User())));
        Mockito.when(this.bookDAO.getById(2)).thenReturn(Optional.of(new Book( 2,
                "Java. Podręcznik na start", "Krzysztof Krocz",
                "978-83-283-9783-5", 44.85,10,new User())));

        int exceptedCartSize = 0;

        this.orderService.confirm();

        Assertions.assertEquals(exceptedCartSize,user.get().getCart().size());
        Mockito.verify(this.bookDAO,Mockito.times(1)).update(new Book( 1,
                "Java. Podręcznik na start", "Krzysztof Krocz",
                "978-83-283-9783-5", 44.85,5,new User()));
        Mockito.verify(this.bookDAO,Mockito.times(1)).update(new Book( 2,
                "Java. Podręcznik na start", "Krzysztof Krocz",
                "978-83-283-9783-5", 44.85,5,new User()));

        Mockito.verify(this.orderDAO, Mockito.times(1)).save(Mockito.any());
    }

    public List<Position> generateFakePosition(){
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(0,new Book( 1,"Java. Podręcznik na start", "Krzysztof Krocz",
                "978-83-283-9783-5", 44.85,5,new User()),5));
        positions.add(new Position(0,new Book( 2,"Java. Podręcznik na start", "Krzysztof Krocz",
                "978-83-283-9783-5", 44.85,5,new User()),10));
        return positions;
    }

    @Test
    void getCurrentUserOrders() {
    }

    @Test
    void getById() {
    }
}