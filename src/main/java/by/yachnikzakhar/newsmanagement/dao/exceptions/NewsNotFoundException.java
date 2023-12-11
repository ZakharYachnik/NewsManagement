package by.yachnikzakhar.newsmanagement.dao.exceptions;

import java.io.Serial;

public class NewsNotFoundException extends Exception{
    @Serial
    private static final long serialVersionUID = -7088854704792041154L;

    public NewsNotFoundException() {
        super();
    }

    public NewsNotFoundException(String message) {
        super(message);
    }

    public NewsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NewsNotFoundException(Throwable cause) {
        super(cause);
    }
}
