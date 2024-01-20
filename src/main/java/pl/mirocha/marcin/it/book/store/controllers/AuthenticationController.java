package pl.mirocha.marcin.it.book.store.controllers;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mirocha.marcin.it.book.store.dao.IUserDAO;
import pl.mirocha.marcin.it.book.store.model.User;
import pl.mirocha.marcin.it.book.store.session.SessionObject;

@Controller
public class AuthenticationController {

    private final IUserDAO userDAO;
/*    @Resource
    SessionObject sessionObject;*/

    public AuthenticationController(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login,@RequestParam String password,
                        HttpSession httpSession) {
        User user = this.userDAO.getByLogin(login);
        if (user != null && user.getPassword().equals(DigestUtils.md5Hex(password))){
            httpSession.setAttribute("logged",true);
            return "redirect:/main";
        }else {
            return "redirect:/login";
        }
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.setAttribute("logged",false);
        return "redirect:/main";
    }
}
