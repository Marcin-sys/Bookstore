package pl.mirocha.marcin.it.book.store.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mirocha.marcin.it.book.store.services.IBookService;

@Controller
public class CommonController {

    private final IBookService iBookService;

    public CommonController(IBookService iBookService) {
        this.iBookService = iBookService;
    }

    @RequestMapping(path = {"/main", "/", "/index"}, method = RequestMethod.GET)
    public String main(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("filter") instanceof String) {
            String pattern = httpSession.getAttribute("filter").toString();
            model.addAttribute("books", iBookService.getByPattern(pattern));
        } else {
            model.addAttribute("books", iBookService.getAll());
        }
        return "index";
    }

    /*     return "redirect:/main";   */
    @RequestMapping(path = "/contact", method = RequestMethod.GET)
    public String contact() {
        return "contact";
    }

    @RequestMapping(path = "/filter", method = RequestMethod.GET)
    public String filter(@RequestParam String pattern, Model model, HttpSession httpSession) {
        if (pattern.isEmpty()) {
            httpSession.removeAttribute("filter");
        } else {
            httpSession.setAttribute("filter", pattern);
        }
        return "redirect:/main";
    }

}

