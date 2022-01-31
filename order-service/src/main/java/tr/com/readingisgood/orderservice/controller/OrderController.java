package tr.com.readingisgood.orderservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.com.readingisgood.orderservice.model.dto.OrderDto;
import tr.com.readingisgood.orderservice.model.dto.OrderStatisticDto;
import tr.com.readingisgood.orderservice.service.OrderService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/readingisgood/orders")
@Tag(name = "Order API documentation", description = "Order API documentation")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private Logger logger = LogManager.getLogger(this.getClass());

    @PostMapping("/")
    @Operation(summary = "New Order placing method")
    public ResponseEntity<String> placeOrder(@Valid @RequestBody OrderDto orderDto, @RequestHeader("authenticated-userid") String authenticatedUserId) {
        logger.debug("The order was placed! ==> " + orderDto);
        orderDto.setUserId(authenticatedUserId);
        orderService.placeOrder(orderDto);
        logger.debug("The order placed successfully! ==> " + orderDto);
        return ResponseEntity.ok().body("The order placed successfully!");
    }

    @GetMapping("/")
    @Operation(summary = "Get all placed orders method")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get placed order by specific id method")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok().body(orderService.getOrder(id));
    }

    @GetMapping("/users/{userId}")
    @Operation(summary = "Get placed orders by specific user method")
    public ResponseEntity<List<OrderDto>> getUserOrders(@PathVariable(name = "userId") String userId) {
        return ResponseEntity.ok().body(orderService.getUserOrders(userId));
    }

    @GetMapping("/statatistics")
    @Operation(summary = "Get all placed orders monthly statics method")
    public ResponseEntity<List<OrderStatisticDto>> getOrderStatics() {
        return ResponseEntity.ok().body(orderService.getMonthlyStatistics());
    }

}
