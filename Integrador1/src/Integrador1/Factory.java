package Integrador1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Factory {
	private List<DAO> lista= new ArrayList<>();
	private String uri;
	
	public Factory(String uri){
		this.uri= uri;
		DAO daoProducto= createDAO();
		DAO daoCliente= createDAO();
		DAO daoFactura= createDAO();
		DAO daoFacturaProducto= createDAO();
		lista.add(daoProducto);
		lista.add(daoCliente);
		lista.add(daoFactura);
		lista.add(daoFacturaProducto);
	}
	
	protected abstract Connection startConnection(String root, String password);
	
	protected abstract DAO createDAO();

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	
}
