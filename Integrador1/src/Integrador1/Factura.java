package Integrador1;

import java.util.ArrayList;

public class Factura {
	private int id;
	private int idCliente;
	private ArrayList<Producto> productos;
	
	public Factura(int idc,int id) {
		this.id= id;
		this.idCliente= idc;
		this.productos= new ArrayList<>();
	}
	
	public void addProducto(Producto p) {
		productos.add(p);
	}
	
	public int getCantidadProductos() {
		return productos.size();
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	public int getId() {
		return id;
	}
	
}
