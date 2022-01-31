package tr.com.readingisgood.userservice.exception;

public class UserAlreadyExistsException extends BaseException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
