package Integrador1;

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

public class DAOCliente extends DAO<Cliente>{
	
	public DAOCliente(Connection conn) {
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
			int idc= Integer.parseInt(row.get("idCliente"));
			String nombre= row.get("nombre");
			String email= row.get("email");
			Cliente c= new Cliente(idc, nombre, email);
			//System.out.println(p);
			this.insert(c);
		}
		
	}
	
	@Override
	public boolean insert(Cliente c) {
		int id= c.getId();
		String nombre= c.getNombre();
		String email= c.getEmail();
		String insert= "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps= this.conn.prepareStatement(insert);
			ps.setInt(1, id);
			ps.setString(2, nombre);
			ps.setString(3, email);
			ps.executeUpdate();
			this.conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
		return true;
	}

	@Override
	public boolean update(Cliente t, String[] params) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Cliente c) {
		try {
			String delete= "DELETE FROM Cliente WHERE id=?";
			PreparedStatement ps= this.conn.prepareStatement(delete);
			ps.setInt(1, c.getId());
			ps.executeUpdate();		
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Cliente select(int id) {
		Cliente c;
		try {
			String select= "SELECT * FROM Cliente WHERE id=?";
			PreparedStatement ps= this.conn.prepareStatement(select);
			ps.setInt(1, id);
			
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				String nombre= rs.getString("nombre");
				String email= rs.getString("email");
				c= new Cliente(id, nombre, email);
				rs.close();
				ps.close();	
				return c;
				// Cierro rs y ps porque si lo pongo abajo del return no se cierran porque corta la ejecucion
			}
			rs.close();
			ps.close();	
			// si no entra al if, cierra igual la rs y ps
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Cliente> selectAll() {
		List<Cliente> resultado= new ArrayList<>();
		try {
			String select= "SELECT * FROM Cliente";
			PreparedStatement ps= this.conn.prepareStatement(select);
			
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				int id= rs.getInt("idCliente");
				String nombre= rs.getString("nombre");
				String email= rs.getString("email");
				Cliente c= new Cliente(id, nombre, email);
				resultado.add(c);
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
		String table= "CREATE TABLE Cliente(" +
		"idCliente INT, " +
		"nombre VARCHAR(500)," +
		"email VARCHAR(500), " +
		"PRIMARY KEY(idCliente))";
		this.conn.prepareStatement(table).execute();
		this.conn.commit();
	}



}
