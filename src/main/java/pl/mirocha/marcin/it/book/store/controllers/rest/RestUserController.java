package pl.mirocha.marcin.it.book.store.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.mirocha.marcin.it.book.store.model.dto.rest.UserDTO;
import pl.mirocha.marcin.it.book.store.services.IUserService;

@RestController
@RequestMapping(path = Constants.API_BASE_URL + "/user")
public class RestUserController {
    private final IUserService userService;

    public RestUserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getById(@PathVariable int id){
        return this.userService.getById(id)
                .map(user -> ResponseEntity.ok(new UserDTO(user)))
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
