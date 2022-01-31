package tr.com.readingisgood.orderservice.exception;

public class BookNotFoundException extends BaseException{
    public BookNotFoundException(String message) {
        super(message);
    }
}
