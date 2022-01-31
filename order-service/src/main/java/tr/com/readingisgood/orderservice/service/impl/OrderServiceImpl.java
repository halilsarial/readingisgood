package tr.com.readingisgood.orderservice.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import tr.com.readingisgood.orderservice.exception.BaseException;
import tr.com.readingisgood.orderservice.model.domain.Order;
import tr.com.readingisgood.orderservice.model.domain.OrderItem;
import tr.com.readingisgood.orderservice.model.dto.OrderDto;
import tr.com.readingisgood.orderservice.model.dto.OrderItemDto;
import tr.com.readingisgood.orderservice.model.dto.OrderStatisticDto;
import tr.com.readingisgood.orderservice.repository.OrderRepository;
import tr.com.readingisgood.orderservice.service.BookService;
import tr.com.readingisgood.orderservice.service.OrderItemService;
import tr.com.readingisgood.orderservice.service.OrderService;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BookService bookService;

    private Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void createOrder(Order order) throws BaseException {
        orderRepository.save(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<OrderDto> orderItems = new ArrayList<>();
        orderRepository.findAll().forEach(order -> orderItems.add(transformDtoFromOrder(order)));
        return orderItems;
    }

    @Override
    public OrderDto getOrder(String id) {
        Order order = getOrderById(id);
        return order != null ? transformDtoFromOrder(order) : null;
    }

    @Override
    public List<OrderDto> getUserOrders(String userId) {
        List<OrderDto> orderItems = new ArrayList<>();
        orderRepository.findByUserId(userId).forEach(order -> orderItems.add(transformDtoFromOrder(order)));
        return orderItems;
    }

    @Override
    public Order getOrderById(String id) throws BaseException {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order transformOrderFromDto(OrderDto orderDto) throws BaseException {
        Order order = new Order();
        order.setUserId(orderDto.getUserId());
        if (CollectionUtils.isEmpty(orderDto.getOrderItems())) {
            logger.error("Order items is empty or null");
            throw new IllegalArgumentException("You should enter order details!");
        }
        order.setTotalPrice(0d);
        Set<OrderItem> orderItems = new HashSet<>();
        orderDto.getOrderItems().forEach(orderItemDto -> {
            OrderItem orderItem = orderItemService.transformOrderItemFromDto(orderItemDto);
            order.setTotalPrice(order.getTotalPrice() + orderItem.getBook().getPrice() * orderItemDto.getQuantity());
            orderItemService.createOrderItem(orderItem);
            orderItems.add(orderItem);
        });
        bookService.updateBookStock(orderDto);
        order.setOrderItems(orderItems);
        return order;
    }

    @Override
    public OrderDto transformDtoFromOrder(Order order) {
        OrderDto orderDto = new OrderDto();
        Set<OrderItem> orderItems = order.getOrderItems();
        Set<OrderItemDto> orderItemDtos = new HashSet<>();
        orderItems.forEach(orderItem -> orderItemDtos.add(orderItemService.transformDtoFromOrderItem(orderItem)));
        orderDto.setOrderItems(orderItemDtos);
        orderDto.setUserId(order.getUserId());
        return orderDto;
    }

    @Override
    //@Transactional
    public void placeOrder(OrderDto orderDto) throws BaseException {
        Order order = transformOrderFromDto(orderDto);
        createOrder(order);
    }

    @Override
    public List<OrderStatisticDto> getMonthlyStatistics() {
        List<OrderStatisticDto> statisticDtos = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);


        for (int i = 0; i < 12; i++) {
            calendar.set(Calendar.MONTH, i);
            Date startDate = calendar.getTime();
            calendar.set(Calendar.MONTH, i + 1);
            Date endDate = calendar.getTime();
            Query query = new Query();
            query.addCriteria(Criteria.where("createTime").lt(endDate).gt(startDate));
            List<Order> orders = mongoTemplate.find(query, Order.class);
            OrderStatisticDto orderStatisticDto = new OrderStatisticDto();
            orderStatisticDto.setTotalOrderCount((long) orders.size());
            orderStatisticDto.setTotalAmount(0d);
            orderStatisticDto.setTotalBookCount(0L);
            orderStatisticDto.setMonth(new SimpleDateFormat("MMM").format(startDate));
            orders.forEach(order -> {
                orderStatisticDto.setTotalAmount(orderStatisticDto.getTotalAmount() + order.getTotalPrice());
                order.getOrderItems().forEach(orderItem -> orderStatisticDto.setTotalBookCount(orderStatisticDto.getTotalBookCount() + orderItem.getQuantity()));
            });
            statisticDtos.add(orderStatisticDto);
        }
        return statisticDtos;
    }
}
