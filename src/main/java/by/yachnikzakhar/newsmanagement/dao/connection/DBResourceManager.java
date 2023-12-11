package by.yachnikzakhar.newsmanagement.dao.connection;

import java.util.Locale;
import java.util.ResourceBundle;

public class DBResourceManager {
	
	private final static DBResourceManager instance = new DBResourceManager();

	private ResourceBundle jdbcProperties = ResourceBundle.getBundle("db");

	public static DBResourceManager getInstance() {
		return instance;
	}
	
	public String getValue(String key) {
		return jdbcProperties.getString(key);
	}

}