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
		
	//creacion de Daos
		DAOProducto daoProd= factory.getDAOProducto();
		DAOFactura daoFac= factory.getDAOFactura();
		DAOCliente daoCli= factory.getDAOCliente();
		DAOFacturaProducto daoFacProd= factory.getDAOFacturaProducto();
		
	//creacion de tablas  (DESCOMENTAR PARA ENTREGAR)
		//daoProd.createTable();
//		daoCli.createTable();
//		daoFac.createTable();
//		daoFacProd.createTable();
		
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
		
	//creacion de relaciones  (DESCOMENTAR PARA ENTREGAR)
		
//		daoFac.createRelationships();
//		daoFacProd.createRelationships();
		
//creacion vistas (DESCOMENTAR PARA CREAR LA VISTA Y QUE ANDE EL PEDIDO SQL PUNTO 4)
		//daoProd.createView();
		//daoCli.createView();
	
		//Prueba fallida (ELIMINAR PARA 1ER ENTREGA)
//		Producto nuevo= new Producto(1, "dfsafs", 1);
//		String[] valores= new String[2];
//		valores[0]= "nisman";
//		valores[1]= "100";
//		daoProd.update(nuevo, valores);
		

		
//Probando producto mas vendido
  Producto p= getTopGrossingProduct(daoProd);
  System.out.println("producto con mas facturacion= "+p); 
  
  System.out.println("");
  
  System.out.println(getTopBilledCustomer(daoCli));
  
  
  
 

  //cerramos coneccion
  factory.closeConnection();
		
 }
	
	public static Producto getTopGrossingProduct(DAOProducto D) throws SQLException {
		
		Producto p= D.ProductoMasVendido();
		
		return p;

	}
	
	public static List<Cliente> getTopBilledCustomer(DAOCliente D) throws SQLException{
		List<Cliente> resultado= D.getFacturacionClientes();
		return resultado;
	}



}
