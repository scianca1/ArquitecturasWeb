package Integrador1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVParser;

public class DAOFactura extends DAO<Factura>{
	
	public DAOFactura(Connection conn) {
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
	public boolean insert(Factura t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Factura t, String[] params) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Factura t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Factura select(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Factura> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void createTable() throws SQLException {
		String table= "CREATE TABLE Factura(" +
		"id INT, " +
		"idCliente INT," +
		"PRIMARY KEY(id))";
		this.conn.prepareStatement(table).execute();
		this.conn.commit();
	}
}

