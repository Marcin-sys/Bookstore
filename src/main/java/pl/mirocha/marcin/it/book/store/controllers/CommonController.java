package pl.mirocha.marcin.it.book.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.mirocha.marcin.it.book.store.dao.IBookDAO;

@Controller
public class CommonController {

    private final IBookDAO bookDAO;

    public CommonController(IBookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @RequestMapping(path = {"/main", "/", "/index"}, method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("books", this.bookDAO.getAll());
        return "index";
    }

    /*
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String main2(){
        return "redirect:/main";
    }
*/
    @RequestMapping(path = "/contact", method = RequestMethod.GET)
    public String contact() {
        return "contact";
    }

}

