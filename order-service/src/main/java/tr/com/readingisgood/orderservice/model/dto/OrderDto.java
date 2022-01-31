package tr.com.readingisgood.orderservice.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Schema(name = "Order model documentation", description = "Placing order is done with this object")
public class OrderDto {

    @Schema(name = "Items field of order object.")
    @Valid
    @NotEmpty(message = "Order items cannot be empty.")
    private Set<OrderItemDto> orderItems;

    @Schema(name = "User who place order id field of order object.")
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
