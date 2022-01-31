package tr.com.readingisgood.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tr.com.readingisgood.orderservice.model.domain.Order;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByUserId(String userId);

    List<Order> findByCreateTimeBetween(Date from, Date to);
}
