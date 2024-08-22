package pl.mirocha.marcin.it.book.store.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.mirocha.marcin.it.book.store.model.dto.rest.PositionDTO;
import pl.mirocha.marcin.it.book.store.services.IPositionService;

@RestController
@RequestMapping(path = Constants.API_BASE_URL + "/position")
public class RestPositionController {

    private final IPositionService positionService;

    public RestPositionController(IPositionService positionService) {
        this.positionService = positionService;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PositionDTO> getById(@PathVariable int id){
        return this.positionService.getById(id).map(position ->ResponseEntity
                        .status(HttpStatus.OK).body(new PositionDTO(position)))
                .orElseGet( ()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
