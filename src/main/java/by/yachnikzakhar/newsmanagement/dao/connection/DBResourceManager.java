package by.yachnikzakhar.newsmanagement.dao.connection;

import java.util.ResourceBundle;

public class DBResourceManager {
	
	private final static DBResourceManager instance = new DBResourceManager();

	ResourceBundle jdbcProperties = ResourceBundle.getBundle("src/main/resources/db.properties");

	public static DBResourceManager getInstance() {
		return instance;
	}
	
	public String getValue(String key) {
		return jdbcProperties.getString(key);
	}

}