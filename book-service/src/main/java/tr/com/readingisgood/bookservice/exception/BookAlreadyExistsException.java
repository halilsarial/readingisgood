package tr.com.readingisgood.bookservice.exception;

public class BookAlreadyExistsException extends BaseException {
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
