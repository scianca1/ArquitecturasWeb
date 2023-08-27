package Integrador1;

import java.sql.Connection;
import java.util.List;


public interface DAO<T> {
	
	public boolean insert(Connection conn, T t);
	
	public boolean update(Connection conn, T t, String[] params);
	
	public boolean delete(Connection conn, T t);
	
	public T select(Connection conn, int id);
	
	public List<T> selectAll(Connection conn);
	

}
