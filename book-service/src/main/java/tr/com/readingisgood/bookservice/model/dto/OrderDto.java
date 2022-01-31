package tr.com.readingisgood.bookservice.model.dto;

import java.util.Set;

public class OrderDto {
    private Set<OrderItemDto> orderItems;
    private String userId;

    public OrderDto() {
    }

    public Set<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderItems=" + orderItems +
                ", userId='" + userId + '\'' +
                '}';
    }
}
