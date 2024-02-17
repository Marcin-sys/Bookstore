package pl.mirocha.marcin.it.book.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.mirocha.marcin.it.book.store.services.ICartService;

@Controller
public class CartController {
    private  final ICartService cartService;

    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(path = "/cart/add/{id}", method = RequestMethod.GET)
    public String add(@PathVariable int id){
        cartService.addBook(id);
        return "redirect:/main";
    }
}
