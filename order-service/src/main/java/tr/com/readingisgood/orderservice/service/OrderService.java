package tr.com.readingisgood.orderservice.service;

import tr.com.readingisgood.orderservice.exception.BaseException;
import tr.com.readingisgood.orderservice.model.domain.Order;
import tr.com.readingisgood.orderservice.model.dto.OrderDto;
import tr.com.readingisgood.orderservice.model.dto.OrderStatisticDto;

import java.util.List;

public interface OrderService {

    void createOrder(Order order) throws BaseException;

    List<OrderDto> getAllOrders();

    OrderDto getOrder(String id);

    List<OrderDto> getUserOrders(String userId);

    Order getOrderById(String id) throws BaseException;

    Order transformOrderFromDto(OrderDto orderDto) throws BaseException;

    OrderDto transformDtoFromOrder(Order order);

    void placeOrder(OrderDto orderDto) throws BaseException;

    List<OrderStatisticDto> getMonthlyStatistics();
}
