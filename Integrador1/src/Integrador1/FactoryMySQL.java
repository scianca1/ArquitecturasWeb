package Integrador1;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryMySQL extends Factory{

	public FactoryMySQL(String uri) {
		super(uri);
	}

	@Override
	protected DAO createDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Connection startConnection(String root, String password) {
		String driver= "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();

		}
		catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		try {
			Connection conn= DriverManager.getConnection(this.getUri(), root, password);
			conn.setAutoCommit(false);
			return conn;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
