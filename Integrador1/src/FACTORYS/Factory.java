package FACTORYS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DAOS.DAO;
import DAOS.DAOCliente;
import DAOS.DAOFactura;
import DAOS.DAOFacturaProducto;
import DAOS.DAOProducto;
import HELPERS.Helper;

public abstract class Factory {
	protected Connection conn;
	private Helper helper;
	protected DAO DAOProducto,DAOCliente,DAOFactura,DAOFacturaProducto;
	
	protected Factory() {
		this.conn= this.startConnection();
		this.helper= new Helper(this.conn);
	}
	
	protected abstract Connection startConnection();
	
	public void closeConnection() throws SQLException{
		this.conn.close();
	}
	
	public DAOCliente getDAOCliente() {
		if(DAOCliente==null) {
		   this.DAOCliente=new DAOCliente(this.conn);
		}
		return (DAOS.DAOCliente) this.DAOCliente;
	}
	
	public DAOFactura getDAOFactura() {
		if(DAOFactura==null) {
		   this.DAOFactura= new DAOFactura(this.conn);
		}
		return (DAOS.DAOFactura) this.DAOFactura;
	}

	public DAOProducto getDAOProducto() {
		if(DAOProducto==null) {
		   this.DAOProducto=new DAOProducto(this.conn);
		}
		return (DAOS.DAOProducto) this.DAOProducto;
	}

	public DAOFacturaProducto getDAOFacturaProducto() {
		if(DAOFacturaProducto==null) {
		  this.DAOFacturaProducto=new DAOFacturaProducto(this.conn);
		}
		return (DAOS.DAOFacturaProducto) this.DAOFacturaProducto;
	}
	public void CreateDB() throws SQLException {
		this.helper.createDB();
	}
	
	
	
}
