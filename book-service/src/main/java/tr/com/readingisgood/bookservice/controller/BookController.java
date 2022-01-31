package tr.com.readingisgood.bookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.readingisgood.bookservice.model.dto.BookDto;
import tr.com.readingisgood.bookservice.model.dto.OrderDto;
import tr.com.readingisgood.bookservice.service.BookService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/readingisgood/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/")
    public ResponseEntity<String> createBook(@Valid @RequestBody BookDto bookDto, @RequestHeader("authenticated-userid") String authenticatedUserId) {
        bookDto.setUpdatedUserId(authenticatedUserId);
        bookService.createBook(bookService.transformBookFromDto(bookDto));
        return ResponseEntity.ok().body("The book is created successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@Valid @RequestBody BookDto bookDto, @RequestHeader("authenticated-userid") String authenticatedUserId) {
        bookDto.setUpdatedUserId(authenticatedUserId);
        bookService.updateBook(bookService.transformBookFromDto(bookDto));
        return ResponseEntity.ok().body("The book is created successfully!");
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable String id) {
        return ResponseEntity.ok().body(bookService.transformDtoFromBook(bookService.getBookById(id)));
    }

    @PostMapping(value = "/update-stock")
    public ResponseEntity<String> updateBookStockForOrder(@Valid @RequestBody OrderDto order) {
        bookService.updateBookStock(order);
        return new ResponseEntity<>("Book stock is updated successfully!", HttpStatus.OK);
    }
}
