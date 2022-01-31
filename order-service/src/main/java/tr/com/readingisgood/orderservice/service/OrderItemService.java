package tr.com.readingisgood.orderservice.service;

import tr.com.readingisgood.orderservice.exception.BaseException;
import tr.com.readingisgood.orderservice.model.domain.OrderItem;
import tr.com.readingisgood.orderservice.model.dto.OrderItemDto;

import java.util.List;

public interface OrderItemService {

    void createOrderItem(OrderItem orderItem) throws BaseException;

    void updateOrderItem(OrderItem orderItem) throws BaseException;

    void deleteOrderItem(OrderItem orderItem) throws BaseException;

    List<OrderItemDto> getAllOrderItems();

    OrderItem getOrderItemById(String id) throws BaseException;

    OrderItem transformOrderItemFromDto(OrderItemDto orderItemDto) throws BaseException;

    OrderItemDto transformDtoFromOrderItem(OrderItem orderItem);
}
