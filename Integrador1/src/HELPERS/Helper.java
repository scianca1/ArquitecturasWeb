package HELPERS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Helper {
	private Connection conn;
	
	public Helper(Connection c) {
		this.conn=c;	}
	
	public void createDB() throws SQLException {
		String tableCliente= "CREATE TABLE Cliente(" +
		"idCliente INT, " +
		"nombre VARCHAR(500)," +
		"email VARCHAR(500), " +
		"PRIMARY KEY(idCliente))";
		PreparedStatement psCliente= this.conn.prepareStatement(tableCliente);
		psCliente.execute();
		this.conn.commit();
		psCliente.close();
		
		String tableFactura= "CREATE TABLE Factura(" +
		"id INT, " +
		"idCliente INT," +
		"PRIMARY KEY(id))";
		PreparedStatement psFactura=this.conn.prepareStatement(tableFactura);
		psFactura.execute();
		this.conn.commit();
		psFactura.close();
		
		String tableFacPro= "CREATE TABLE FacturaProducto(" +
		"idFactura INT, " +
		"idProducto INT," +
		"cantidad INT, " +
		"PRIMARY KEY(idFactura, idProducto))";
		PreparedStatement psFacPro= this.conn.prepareStatement(tableFacPro);
		psFacPro.execute();
		this.conn.commit();
		psFacPro.close();
		
		String tableProd= "CREATE TABLE Producto(" +
		"id INT, " +
		"nombre VARCHAR(500)," +
		"valor INT, " +
		"PRIMARY KEY(id))";
		PreparedStatement psProd= this.conn.prepareStatement(tableProd);
		psProd.execute();
		this.conn.commit();
		psProd.close();
		
		String fk1= "ALTER TABLE FacturaProducto ADD FOREIGN KEY (idProducto) REFERENCES Producto (id)";
		PreparedStatement psFacProdRel= this.conn.prepareStatement(fk1);
		psFacProdRel.execute();
		this.conn.commit();
		psFacProdRel.close();
		String fk2= "ALTER TABLE FacturaProducto ADD FOREIGN KEY (idFactura) REFERENCES Factura (id)";
		PreparedStatement psFacProdRel2= this.conn.prepareStatement(fk2);
		psFacProdRel2.execute();
		this.conn.commit();
		psFacProdRel2.close();
		
		String fk= "ALTER TABLE Factura ADD FOREIGN KEY (idCliente) REFERENCES Cliente (idCliente)";
		PreparedStatement psFac= this.conn.prepareStatement(fk);
		psFac.execute();
		this.conn.commit();
		psFac.close();
		
		String sentenciaViewProd= 	"create view vistaproductosxcantidad AS "+
		"select idProducto,sum(cantidad)as total "+
		"from facturaproducto "+
		"group by idProducto "+
		"ORDER by total DESC;";
		PreparedStatement psViewProd= this.conn.prepareStatement(sentenciaViewProd);
		psViewProd.execute();
		this.conn.commit();
		psViewProd.close();
		
		String sentenciaViewCli= "create view vistaprecioxfactura AS "
		+ "select fp.idFactura,sum(p.valor*fp.cantidad) as valor"
		+ "	from facturaproducto fp inner join producto p"
		+ "	on fp.idProducto=p.id"
		+ "	group by fp.idFactura;";
		PreparedStatement psViewCli =this.conn.prepareStatement(sentenciaViewCli);
		psViewCli.execute();
		this.conn.commit();
		psViewCli.close();
		
		
	}
	

}
