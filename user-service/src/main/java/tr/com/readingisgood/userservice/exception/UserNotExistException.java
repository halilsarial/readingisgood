package tr.com.readingisgood.userservice.exception;

public class UserNotExistException extends BaseException {
    public UserNotExistException(String message) {
        super(message);
    }
}
