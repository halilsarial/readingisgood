package tr.com.readingisgood.bookservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tr.com.readingisgood.bookservice.model.domain.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
