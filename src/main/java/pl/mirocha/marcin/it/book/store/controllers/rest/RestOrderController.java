package pl.mirocha.marcin.it.book.store.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.mirocha.marcin.it.book.store.model.dto.rest.OrderDTO;
import pl.mirocha.marcin.it.book.store.services.IOrderService;

@RestController
@RequestMapping(path = Constants.API_BASE_URL + "/order")
public class RestOrderController {

    private final IOrderService orderService;

    public RestOrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderDTO> getById(@PathVariable int id){
        return this.orderService.getById(id).map(order ->ResponseEntity
                .status(HttpStatus.OK).body(new OrderDTO(order)))
                .orElseGet( ()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
