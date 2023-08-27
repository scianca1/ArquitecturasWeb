package Integrador1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Factory {
	
	public abstract Connection startConnection();
	
	public abstract void closeConnection(Connection conn) throws SQLException;
	
	public DAOCliente getDAOCliente() {
		return new DAOCliente();
	}
	
	public DAOFactura getDAOFactura() {
		return new DAOFactura();
	}

	public DAOProducto getDAOProducto() {
		return new DAOProducto();
	}

	public DAOFacturaProducto getDAOFacturaProducto() {
		return new DAOFacturaProducto();
	}
	
	
}
