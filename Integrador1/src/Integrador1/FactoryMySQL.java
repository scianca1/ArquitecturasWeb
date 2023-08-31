package Integrador1;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryMySQL extends Factory{
	public static final String driver= "com.mysql.cj.jdbc.Driver";
	public static final String url= "jdbc:mysql://localhost:3306/integrador1";

	public FactoryMySQL() {
		super();
	}
	@Override
	protected Connection startConnection() {
		
		try {
			Class.forName(this.driver).getDeclaredConstructor().newInstance();

		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		try {
			Connection conn= DriverManager.getConnection(this.url, "root", "");
			conn.setAutoCommit(false);
			return conn;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	

}
