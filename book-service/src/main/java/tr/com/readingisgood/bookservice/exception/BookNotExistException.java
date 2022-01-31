package tr.com.readingisgood.bookservice.exception;

public class BookNotExistException extends BaseException {
    public BookNotExistException(String message) {
        super(message);
    }
}
