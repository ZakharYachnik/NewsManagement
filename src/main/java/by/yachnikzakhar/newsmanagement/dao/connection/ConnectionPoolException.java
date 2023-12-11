package by.yachnikzakhar.newsmanagement.dao.connection;

import java.io.Serial;

public class ConnectionPoolException extends Exception{

	@Serial
	private static final long serialVersionUID = 991793500838231109L;

	public ConnectionPoolException (String message, Exception e) {
		super(message, e);
	}

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(String message) {
		super(message);
	}

	public ConnectionPoolException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConnectionPoolException(Throwable cause) {
		super(cause);
	}
}
