package tr.com.readingisgood.bookservice.service;

import tr.com.readingisgood.bookservice.exception.BaseException;
import tr.com.readingisgood.bookservice.model.domain.Book;
import tr.com.readingisgood.bookservice.model.dto.BookDto;
import tr.com.readingisgood.bookservice.model.dto.OrderDto;

import java.util.List;
import java.util.Set;

public interface BookService {

    void createBook(Book book) throws BaseException;

    void updateBook(Book book) throws BaseException;

    void deleteBook(Book book) throws BaseException;

    void deleteBookById(String id) throws BaseException;

    List<BookDto> getAllBooks();

    Book getBookById(String id) throws BaseException;

    Book getBookByName(String name) throws BaseException;

    Book transformBookFromDto(BookDto bookDto);

    BookDto transformDtoFromBook(Book book);

    void updateBookStock(OrderDto order) throws BaseException;
}
