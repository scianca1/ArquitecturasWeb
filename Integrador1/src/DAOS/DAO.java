package DAOS;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.csv.CSVParser;


public abstract class DAO<T> {
	protected Connection conn;
	
	protected DAO(Connection c) {
		this.conn= c;
	}
	
	public abstract void readCSV(String csv) throws SQLException;
	
	protected abstract void create(CSVParser parser) throws SQLException;
	
	public abstract boolean insert(T t);
	
	public abstract boolean update(T t, String[] params);
	
	public abstract boolean delete(T t);
	
	public abstract T select(int id);
	
	public abstract List<T> selectAll();
	
	public abstract void createTable() throws SQLException;


}
