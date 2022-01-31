package tr.com.readingisgood.bookservice.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tr.com.readingisgood.bookservice.exception.BaseException;
import tr.com.readingisgood.bookservice.exception.BookAlreadyExistsException;
import tr.com.readingisgood.bookservice.exception.BookNotExistException;
import tr.com.readingisgood.bookservice.exception.OutOfStockException;
import tr.com.readingisgood.bookservice.model.domain.Book;
import tr.com.readingisgood.bookservice.model.dto.BookDto;
import tr.com.readingisgood.bookservice.model.dto.OrderDto;
import tr.com.readingisgood.bookservice.repository.BookRepository;
import tr.com.readingisgood.bookservice.service.BookService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void createBook(Book book) throws BaseException {
        if (book.getId() != null && getBookById(book.getId()) != null) {
            throw new BookAlreadyExistsException("The book already exists!");
        } else {
            bookRepository.save(book);
        }
    }

    @Override
    public void updateBook(Book book) throws BaseException {
        if (book.getId() == null || getBookById(book.getId()) == null) {
            throw new BookNotExistException("The book does not exist!");
        } else {
            bookRepository.save(book);
        }
    }

    @Override
    public void deleteBook(Book book) throws BaseException {
        if (book.getId() == null || getBookById(book.getId()) == null) {
            throw new BookNotExistException("The book does not exist!");
        } else {
            bookRepository.delete(book);
        }
    }

    @Override
    public void deleteBookById(String id) throws BaseException {

    }

    @Override
    public List<BookDto> getAllBooks() {
        List<BookDto> bookDtos = new ArrayList<>();
        bookRepository.findAll().forEach(book -> bookDtos.add(transformDtoFromBook(book)));
        return bookDtos;
    }

    @Override
    public Book getBookById(String id) throws BaseException {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book getBookByName(String name) throws BaseException {
        return null;
    }

    @Override
    public Book transformBookFromDto(BookDto bookDto) {
        Book book = new Book();
        book.setName(StringUtils.isNotEmpty(bookDto.getName()) ? bookDto.getName().trim().toUpperCase() : StringUtils.EMPTY);
        book.setUpdatedUserId(bookDto.getUpdatedUserId());
        book.setAuthor(bookDto.getAuthor());
        book.setType(bookDto.getType());
        book.setPrice(bookDto.getPrice());
        book.setStock(bookDto.getStock());
        book.setDescription(bookDto.getDescription());
        return book;
    }

    @Override
    public BookDto transformDtoFromBook(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setCreateTime(book.getCreateTime());
        bookDto.setUpdateTime(book.getUpdateTime());
        bookDto.setVersion(book.getVersion());
        bookDto.setUpdatedUserId(book.getUpdatedUserId());
        bookDto.setName(StringUtils.isNotEmpty(book.getName()) ? book.getName().trim().toUpperCase() : StringUtils.EMPTY);
        bookDto.setAuthor(book.getAuthor());
        bookDto.setType(book.getType());
        bookDto.setPrice(book.getPrice());
        bookDto.setStock(book.getStock());
        bookDto.setDescription(book.getDescription());
        return bookDto;
    }

    @Override
    //@Transactional
    public void updateBookStock(OrderDto order) throws BaseException {
        if (order == null || CollectionUtils.isEmpty(order.getOrderItems())) {
            return;
        }
        order.getOrderItems().forEach(orderItem -> {
            Book book = getBookById(orderItem.getBookId());
            if (book == null) {
                throw new BookNotExistException("The book (id = " + orderItem.getBookId() + ") not exist");
            }
            if (book.getStock() < orderItem.getQuantity()) {
                throw new OutOfStockException("Stock is not enough for the book (id = " + book.getId() + ")");
            }
            book.setStock(book.getStock() - orderItem.getQuantity());
            updateBook(book);
        });
    }

}
