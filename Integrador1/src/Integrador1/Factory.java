package Integrador1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Factory {
	protected Connection conn;
	
	public Factory() {
		this.conn= this.startConnection();
	}
	
	protected abstract Connection startConnection();
	
	public void closeConnection() throws SQLException{
		this.conn.close();
	}
	
	public DAOCliente getDAOCliente() {
		return new DAOCliente(this.conn);
	}
	
	public DAOFactura getDAOFactura() {
		return new DAOFactura(this.conn);
	}

	public DAOProducto getDAOProducto() {
		return new DAOProducto(this.conn);
	}

	public DAOFacturaProducto getDAOFacturaProducto() {
		return new DAOFacturaProducto(this.conn);
	}
	
	
}
