package Integrador1;

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

public class DAOProducto implements DAO<Producto>{
	

	@Override
	public boolean insert(Connection conn, Producto p) {
		String nombre= p.getNombre();
		int valor= p.getValor();
		int id= p.getId();
		String insert= "INSERT INTO Producto (id, nombre, valor) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps= conn.prepareStatement(insert);
			ps.setInt(1, id);
			ps.setString(2, nombre);
			ps.setInt(3, valor);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Connection conn, Producto p, String[] params) {
		try {
			p.setNombre(Objects.requireNonNull(params[0], "El nombre no puede ser nulo"));
			p.setValor(Objects.requireNonNull(Integer.parseInt(params[1]), "El valor no puede ser nulo"));
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		try {
			String update= "UPDATE Producto SET nombre= ? AND valor=? WHERE id=?";
			PreparedStatement ps= conn.prepareStatement(update);
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getValor());
			ps.setInt(3, p.getId());
			ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Connection conn, Producto p) {
		try {
			String delete= "DELETE FROM Producto WHERE id=?";
			PreparedStatement ps= conn.prepareStatement(delete);
			ps.setInt(1, p.getId());
			ps.executeUpdate();		
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Producto select(Connection conn, int id) {
		Producto p;
		try {
			String select= "SELECT FROM Producto WHERE id=?";
			PreparedStatement ps= conn.prepareStatement(select);
			ps.setInt(1, id);
			
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				String nombre= rs.getString("nombre");
				int valor= rs.getInt("valor");
				p= new Producto(id, nombre, valor);
				return p;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Producto> selectAll(Connection conn) {
		List<Producto> resultado= new ArrayList<>();
		try {
			String select= "SELECT * FROM Producto";
			PreparedStatement ps= conn.prepareStatement(select);
			
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				int id= rs.getInt("id");
				String nombre= rs.getString("nombre");
				int valor= rs.getInt("valor");
				Producto p= new Producto(id, nombre, valor);
				resultado.add(p);
			}
			return resultado;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
