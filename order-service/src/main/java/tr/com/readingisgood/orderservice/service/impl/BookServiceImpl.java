package tr.com.readingisgood.orderservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tr.com.readingisgood.orderservice.exception.BaseException;
import tr.com.readingisgood.orderservice.model.domain.Book;
import tr.com.readingisgood.orderservice.model.dto.OrderDto;
import tr.com.readingisgood.orderservice.repository.BookRepository;
import tr.com.readingisgood.orderservice.service.BookService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Book getBookById(String id) throws BaseException {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void updateBookStock(OrderDto order) throws BaseException {
        HttpEntity<OrderDto> request = new HttpEntity<>(order);
        restTemplate.postForLocation("http://BOOK-SERVICE/books/update-stock", request);
    }
}
