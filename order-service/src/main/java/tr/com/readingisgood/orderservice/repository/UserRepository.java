package tr.com.readingisgood.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tr.com.readingisgood.orderservice.model.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
}
