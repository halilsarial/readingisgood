package tr.com.readingisgood.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tr.com.readingisgood.orderservice.model.domain.OrderItem;

public interface OrderItemRepository extends MongoRepository<OrderItem, String> {
}
