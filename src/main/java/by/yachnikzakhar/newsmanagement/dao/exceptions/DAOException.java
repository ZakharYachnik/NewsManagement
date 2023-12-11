package by.yachnikzakhar.newsmanagement.dao.exceptions;

import java.io.Serial;

public class DAOException extends Exception {
    @Serial
    private static final long serialVersionUID = -3874524655572981888L;

    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
