package Integrador1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVParser;

public class DAOFacturaProducto extends DAO<FacturaProducto>{
	
	public DAOFacturaProducto(Connection conn) {
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
	public boolean insert(FacturaProducto t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(FacturaProducto t, String[] params) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(FacturaProducto t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FacturaProducto select(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FacturaProducto> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void createTable() throws SQLException {
		String table= "CREATE TABLE FacturaProducto(" +
		"idFactura INT, " +
		"idProducto INT," +
		"cantidad INT, " +
		"PRIMARY KEY(idFactura, idProducto))";
		this.conn.prepareStatement(table).execute();
		this.conn.commit();
	}

}
