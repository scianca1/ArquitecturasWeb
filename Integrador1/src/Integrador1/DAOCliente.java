package Integrador1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVParser;

public class DAOCliente extends DAO<Cliente>{
	
	public DAOCliente(Connection conn) {
		super(conn);
	}

	@Override
	public void readCSV(String csv) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void create(CSVParser parser) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean insert(Cliente t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Cliente t, String[] params) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Cliente t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cliente select(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void createTable() throws SQLException {
		String table= "CREATE TABLE Cliente(" +
		"id INT, " +
		"nombre VARCHAR(500)," +
		"email VARCHAR(500), " +
		"PRIMARY KEY(id))";
		this.conn.prepareStatement(table).execute();
		this.conn.commit();
	}



}
