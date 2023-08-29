package Integrador1;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class DAOFacturaProducto extends DAO<FacturaProducto>{
	
	public DAOFacturaProducto(Connection conn) {
		super(conn);
	}
	
	@Override
	public void readCSV(String csv) throws SQLException {
		CSVParser parser;
		try {
			parser= CSVFormat.DEFAULT.withHeader().parse(new FileReader(csv));
			this.create(parser);
			//this.conn.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void create(CSVParser parser) throws SQLException {
		for(CSVRecord row: parser) {
			int idF= Integer.parseInt(row.get("idFactura"));
			int idp= Integer.parseInt(row.get("idProducto"));
			int cant= Integer.parseInt(row.get("cantidad"));
			FacturaProducto f= new FacturaProducto(idF, idp, cant);
			//System.out.println(p);
			this.insert(f);
		}
		
	}

	@Override
	public boolean insert(FacturaProducto t) {
		int idF= t.getIdFactura();
		int idP= t.getIdProducto();
		int cant= t.getCantidad();
		String insert= "INSERT INTO FacturaProducto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps= this.conn.prepareStatement(insert);
			ps.setInt(1, idF);
			ps.setInt(2, idP);
			ps.setInt(3, cant);
			ps.executeUpdate();
			this.conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
		return true;
	}

	@Override
	public boolean update(FacturaProducto t, String[] params) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(FacturaProducto t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FacturaProducto select(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FacturaProducto> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void createTable() throws SQLException {
		String table= "CREATE TABLE FacturaProducto(" +
		"idFactura INT, " +
		"idProducto INT," +
		"cantidad INT, " +
		"PRIMARY KEY(idFactura, idProducto))";
		this.conn.prepareStatement(table).execute();
		this.conn.commit();
	}
	
	public void createRelationships() throws SQLException {
		String fk= "ALTER TABLE FacturaProducto ADD FOREIGN KEY (idProducto) REFERENCES Producto (id)";
		this.conn.prepareStatement(fk).execute();
		this.conn.commit();
		String fk2= "ALTER TABLE FacturaProducto ADD FOREIGN KEY (idFactura) REFERENCES Factura (id)";
		this.conn.prepareStatement(fk2).execute();
		this.conn.commit();
	}

}
