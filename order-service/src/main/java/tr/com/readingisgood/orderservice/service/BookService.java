package tr.com.readingisgood.orderservice.service;


import tr.com.readingisgood.orderservice.exception.BaseException;
import tr.com.readingisgood.orderservice.model.domain.Book;
import tr.com.readingisgood.orderservice.model.dto.OrderDto;

public interface BookService {

    Book getBookById(String id) throws BaseException;

    void updateBookStock(OrderDto order) throws BaseException;
}
