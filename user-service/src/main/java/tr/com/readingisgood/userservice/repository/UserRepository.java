package tr.com.readingisgood.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tr.com.readingisgood.userservice.model.domain.User;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ 'userName': ?0}")
    List<User> findByUserName(String userName);
}
