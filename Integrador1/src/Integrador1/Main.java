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

import DAOS.DAOCliente;
import DAOS.DAOFactura;
import DAOS.DAOFacturaProducto;
import DAOS.DAOProducto;
import FACTORYS.Factory;
import FACTORYS.FactoryMySQL;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
		
		// ACLARACION= Para que la base de datos de genere correctamente sera necesario que en la clase 
		//FactoryMySQL, se cambie la variable url (url= "jdbc:mysql://localhost:3306/integrador1")
		//a la ruta donde decea que se armen las tablas 
		
		Factory factory= FactoryMySQL.getInstance();
		//creacion de tablas 
//		factory.CreateDB();
		
	//creacion de Daos
		DAOProducto daoProd= factory.getDAOProducto();
        DAOFactura daoFac= factory.getDAOFactura();
		DAOCliente daoCli= factory.getDAOCliente();
		DAOFacturaProducto daoFacProd= factory.getDAOFacturaProducto();
		

	//creaccion de rutas de archivos
		String csvProd= "../Integrador1/src/datasets/productos.csv";
		String csvFac= "../Integrador1/src/datasets/facturas.csv";
		String csvCli= "../Integrador1/src/datasets/clientes.csv";
		String csvFacProd= "../Integrador1/src/datasets/facturas-productos.csv";
		
	//insercion de datos de los exels  (DESCOMENTAR PARA ENTREGAR)	
		
//		daoProd.readCSV(csvProd);
//		daoCli.readCSV(csvCli);
//		daoFac.readCSV(csvFac);
//		daoFacProd.readCSV(csvFacProd);

		
		// Producto mas vendido
		  Producto p= daoProd.ProductoMasVendido();
		  System.out.println("producto con mas facturacion= "+p); 
		  
		  System.out.println("");
		  
		//  Clientes ordenados de mayor a menor facturacion total 
		  
		
		  List<Cliente> resultado= daoCli.getFacturacionClientes();
		  System.out.println(resultado);
		  
		 
		
		  //cerramos coneccion
		  factory.closeConnection();
		
 }
	




}
