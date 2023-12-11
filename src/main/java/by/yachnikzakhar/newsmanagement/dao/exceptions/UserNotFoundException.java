package by.yachnikzakhar.newsmanagement.dao.exceptions;

import java.io.Serial;

public class UserNotFoundException extends Exception{
    @Serial
    private static final long serialVersionUID = -8915001326611435127L;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
