package tr.com.readingisgood.orderservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tr.com.readingisgood.orderservice.model.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
