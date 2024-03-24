package pl.mirocha.marcin.it.book.store.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mirocha.marcin.it.book.store.exceptions.UserNotExistException;
import pl.mirocha.marcin.it.book.store.model.Book;
import pl.mirocha.marcin.it.book.store.model.dto.rest.BookDTO;
import pl.mirocha.marcin.it.book.store.model.dto.rest.BookListDTO;
import pl.mirocha.marcin.it.book.store.services.IBookService;

import java.util.List;

@RestController
@RequestMapping(path = Constants.API_BASE_URL + "/book")
public class RestBookController {

    private final IBookService bookService;

    public RestBookController(IBookService iBookService) {
        this.bookService = iBookService;
    }

    @RequestMapping(path = "",method = RequestMethod.POST)
    public ResponseEntity<BookDTO> save(@RequestBody BookDTO book){
        //201
        try {
            BookDTO bookDTO = this.bookService.save(book);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(this.bookService.save(book));
        }catch (UserNotExistException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

    }
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BookDTO> getById(@PathVariable int id){/*
        Optional<Book> bookBox = this.bookService.getById(id);
        if (bookBox.isPresent()){
            return ResponseEntity
                    .ok(new BookDTO(bookBox.get()));
        }else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }*/

        return this.bookService.getById(id)
                .map(book -> ResponseEntity.ok(new BookDTO(book)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }
    @RequestMapping(path = "/id",method = RequestMethod.PUT)
    public BookDTO update(@PathVariable int id,
                          @RequestBody BookDTO bookDTO){
        return this.bookService.update(id,bookDTO);
    }
    @RequestMapping(path = "",method = RequestMethod.GET)
    public BookListDTO getByPattern(@RequestParam(required = false) String pattern){
        List<Book> books;
        if (pattern == null){
            books = this.bookService.getAll();
        }else {
            books = this.bookService.getByPattern(pattern);
        }
        BookListDTO result = new BookListDTO();
        result.getBooks().addAll(books.stream().map(book -> new BookDTO(book)).toList());
        return result;
    }
}
