package FACTORYS;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryMySQL extends Factory{
	private static final String driver= "com.mysql.cj.jdbc.Driver";
	private static final String url= "jdbc:mysql://localhost:3306/integrador1";
	private static Factory instance= new FactoryMySQL();
	

	private FactoryMySQL() {
		super();
	}
	public static Factory getInstance() {
		return instance;
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
