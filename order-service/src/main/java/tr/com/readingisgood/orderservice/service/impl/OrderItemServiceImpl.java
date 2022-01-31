package tr.com.readingisgood.orderservice.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.readingisgood.orderservice.exception.AlreadyExistsException;
import tr.com.readingisgood.orderservice.exception.BaseException;
import tr.com.readingisgood.orderservice.exception.BookNotFoundException;
import tr.com.readingisgood.orderservice.exception.NotExistException;
import tr.com.readingisgood.orderservice.model.domain.Book;
import tr.com.readingisgood.orderservice.model.domain.OrderItem;
import tr.com.readingisgood.orderservice.model.dto.OrderItemDto;
import tr.com.readingisgood.orderservice.repository.OrderItemRepository;
import tr.com.readingisgood.orderservice.service.BookService;
import tr.com.readingisgood.orderservice.service.OrderItemService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private BookService bookService;

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void createOrderItem(OrderItem orderItem) throws BaseException {
        if (orderItem.getId() != null && getOrderItemById(orderItem.getId()) != null) {
            logger.debug("The order item already exists!" + orderItem);
            throw new AlreadyExistsException("The order item already exists!");
        }
        orderItemRepository.save(orderItem);
        logger.info("The order item was created!" + orderItem);
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) throws BaseException {
        if (orderItem.getId() == null || getOrderItemById(orderItem.getId()) == null) {
            throw new NotExistException("The order ite does not exist! " + orderItem);
        }
        orderItemRepository.save(orderItem);
        logger.info("The order item was updated!" + orderItem);
    }

    @Override
    public void deleteOrderItem(OrderItem orderItem) throws BaseException {
        if (orderItem.getId() == null || getOrderItemById(orderItem.getId()) == null) {
            throw new NotExistException("The order ite does not exist! " + orderItem);
        }
        orderItemRepository.save(orderItem);
        logger.info("The order item was deleted!" + orderItem);
    }

    @Override
    public List<OrderItemDto> getAllOrderItems() {
        List<OrderItemDto> orderItems = new ArrayList<>();
        orderItemRepository.findAll().forEach(orderItem -> orderItems.add(transformDtoFromOrderItem(orderItem)));
        return orderItems;
    }

    @Override
    public OrderItem getOrderItemById(String id) throws BaseException {
        return orderItemRepository.findById(id).orElse(null);
    }

    @Override
    public OrderItem transformOrderItemFromDto(OrderItemDto orderItemDto) throws BaseException {
        OrderItem orderItem = new OrderItem();
        Book book = bookService.getBookById(orderItemDto.getBookId());
        if (book == null) {
            throw new BookNotFoundException("The book that is placed order not found!");
        }
        orderItem.setBook(book);
        orderItem.setQuantity(orderItemDto.getQuantity());
        return orderItem;
    }

    @Override
    public OrderItemDto transformDtoFromOrderItem(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setBookId(orderItem.getBook().getId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        return orderItemDto;
    }
}
