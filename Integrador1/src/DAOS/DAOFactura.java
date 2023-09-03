package DAOS;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import Integrador1.Factura;

public class DAOFactura extends DAO<Factura>{
	
	public DAOFactura(Connection conn) {
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
			int idf= Integer.parseInt(row.get("idFactura"));
			int idc= Integer.parseInt(row.get("idCliente"));
			Factura f= new Factura(idc,idf);
			//System.out.println(p);
			this.insert(f);
		}
		
	}

	@Override
	public boolean insert(Factura t) {
		int id= t.getId();
		int idc= t.getIdCliente();
		String insert= "INSERT INTO Factura (id, idCliente) VALUES (?, ?)";
		try {
			PreparedStatement ps= this.conn.prepareStatement(insert);
			ps.setInt(1, id);
			ps.setInt(2, idc);
			ps.executeUpdate();
			this.conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
		return true;
	}

	@Override
	public boolean update(Factura t, String[] params) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Factura f) {
		try {
			String delete= "DELETE FROM Factura WHERE id=?";
			PreparedStatement ps= this.conn.prepareStatement(delete);
			ps.setInt(1, f.getId());
			ps.executeUpdate();		
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Factura select(int id) {
		Factura f;
		try {
			String select= "SELECT * FROM Factura WHERE id=?";
			PreparedStatement ps= this.conn.prepareStatement(select);
			ps.setInt(1, id);
			
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				int idC= rs.getInt("idCliente");
				f= new Factura(id, idC);
				rs.close();
				ps.close();
				return f;
			}
			rs.close();
			ps.close();	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Factura> selectAll() {
		List<Factura> resultado= new ArrayList<>();
		try {
			String select= "SELECT * FROM Factura";
			PreparedStatement ps= this.conn.prepareStatement(select);
			
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				int id= rs.getInt("id");
				int idC= rs.getInt("idCliente");
				Factura f= new Factura(id, idC);
				resultado.add(f);
			}
			rs.close();
			ps.close();
			return resultado;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public void createTable() throws SQLException {
		String table= "CREATE TABLE Factura(" +
		"id INT, " +
		"idCliente INT," +
		"PRIMARY KEY(id))";
		this.conn.prepareStatement(table).execute();
		this.conn.commit();
	}
	
	public void createRelationships() throws SQLException {
		String fk= "ALTER TABLE Factura ADD FOREIGN KEY (idCliente) REFERENCES Cliente (idCliente)";
		this.conn.prepareStatement(fk).execute();
		this.conn.commit();
	}
	
}

