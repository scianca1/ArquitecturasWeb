package Integrador1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
		Factory factory= new FactoryMySQL();
		DAO daoProd= factory.getDAOProducto();
		
		//daoProd.createTable();
		//String csvProd= "../Integrador1/src/datasets/productos.csv";
		//daoProd.readCSV(csvProd);
		//System.out.println(daoProd.selectAll());
		/*
		Producto nuevo= new Producto(1, "dfsafs", 1);
		String[] valores= new String[2];
		valores[0]= "nisman";
		valores[1]= "100";
		daoProd.update(nuevo, valores);
		daoCli.createTable();
		daoFac.createTable();
		daoFacProd.createTable();
		 */
		
		DAOFactura daoFac= factory.getDAOFactura();
		DAO daoCli= factory.getDAOCliente();
		DAOFacturaProducto daoFacProd= factory.getDAOFacturaProducto();
		
		//String csvFac= "../Integrador1/src/datasets/facturas.csv";
		//String csvCli= "../Integrador1/src/datasets/clientes.csv";
		//String csvFacProd= "../Integrador1/src/datasets/facturas-productos.csv";
		
		//daoCli.readCSV(csvCli);
		//daoFac.readCSV(csvFac);
		//daoFacProd.readCSV(csvFacProd);
		
		daoFac.createRelationships();
		daoFacProd.createRelationships();
		
		factory.closeConnection();
	}
	
	public Producto getTopGrossingProduct() {
		Producto p;
		return p;
	}
	
	public List<Cliente> getTopBilledCustomer(){
		List<Cliente> resultado= new ArrayList<>();
		return resultado;
	}


}
