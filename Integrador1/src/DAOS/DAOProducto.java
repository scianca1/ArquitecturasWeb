package DAOS;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.sql.RowSet;
import javax.sql.rowset.RowSetProvider;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import Integrador1.Producto;


public class DAOProducto extends DAO<Producto>{
	
	public DAOProducto(Connection conn) {
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
	protected void create(CSVParser parser) throws SQLException {
		for(CSVRecord row: parser) {
			int id= Integer.parseInt(row.get("idProducto"));
			String nombre= row.get("nombre");
			int valor= Integer.parseInt(row.get("idProducto"));
			Producto p= new Producto(id, nombre, valor);
			//System.out.println(p);
			this.insert(p);
		}
		
	}

	@Override
	public boolean insert(Producto p) {
		String nombre= p.getNombre();
		int valor= p.getValor();
		int id= p.getId();
		String insert= "INSERT INTO Producto (id, nombre, valor) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps= this.conn.prepareStatement(insert);
			ps.setInt(1, id);
			ps.setString(2, nombre);
			ps.setInt(3, valor);
			ps.executeUpdate();
			this.conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
		return true;
	}

	@Override
	public boolean update(Producto p, String[] params) {
		try {
			p.setNombre(Objects.requireNonNull(params[0], "El nombre no puede ser nulo"));
			p.setValor(Objects.requireNonNull(Integer.parseInt(params[1]), "El valor no puede ser nulo"));
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		try {
			String update= "UPDATE Producto SET nombre= ? AND valor=? WHERE id=?";
			PreparedStatement ps= this.conn.prepareStatement(update);
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getValor());
			ps.setInt(3, p.getId());
			ps.executeUpdate();
			this.conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Producto p) {
		try {
			String delete= "DELETE FROM Producto WHERE id=?";
			PreparedStatement ps= this.conn.prepareStatement(delete);
			ps.setInt(1, p.getId());
			ps.executeUpdate();		
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Producto select(int id) {
		Producto p;
		try {
			String select= "SELECT * FROM Producto WHERE id=?";
			PreparedStatement ps= this.conn.prepareStatement(select);
			ps.setInt(1, id);
			
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				String nombre= rs.getString("nombre");
				int valor= rs.getInt("valor");
				p= new Producto(id, nombre, valor);
				rs.close();
				ps.close();
				return p;
			}
			rs.close();
			ps.close();	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Producto> selectAll() {
		List<Producto> resultado= new ArrayList<>();
		try {
			String select= "SELECT * FROM Producto";
			PreparedStatement ps= this.conn.prepareStatement(select);
			
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				int id= rs.getInt("id");
				String nombre= rs.getString("nombre");
				int valor= rs.getInt("valor");
				Producto p= new Producto(id, nombre, valor);
				resultado.add(p);
			}
			rs.close();
			ps.close();
			return resultado;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public void createTable() throws SQLException {
		String table= "CREATE TABLE Producto(" +
		"id INT, " +
		"nombre VARCHAR(500)," +
		"valor INT, " +
		"PRIMARY KEY(id))";
		this.conn.prepareStatement(table).execute();
		this.conn.commit();
	}
	
	public Producto ProductoMasVendido() throws SQLException {
		String sentencia="SELECT p.id,(p.valor*v.total)as facturado,p.nombre,p.valor from producto p\r\n"
				+ "inner join vistaproductosxcantidad as v on v.idProducto=p.id\r\n"
				+ "order by facturado DESC\r\n"
				+ "limit 1;";
		PreparedStatement ps=this.conn.prepareStatement(sentencia);
		ResultSet resultado= ps.executeQuery();
		if(resultado.next()) {
			int id= resultado.getInt("id");
			String nombre= resultado.getString("nombre");
			int valor= resultado.getInt("valor");
			int facturado= resultado.getInt("facturado");
			Producto p= new Producto(id,nombre,valor);
			p.setFacturado(facturado);
			resultado.close();
			ps.close();
			return p;
		}
		return null;
	}
	
	public void createView() throws SQLException {
		String sentencia= 	"create view vistaproductosxcantidad AS "+
				"select idProducto,sum(cantidad)as total "+
				"from facturaproducto "+
				"group by idProducto "+
				"ORDER by total DESC;";
		PreparedStatement ps=this.conn.prepareStatement(sentencia);
		ps.execute();
		this.conn.commit();
		ps.close();
		
		
	}
	


}
