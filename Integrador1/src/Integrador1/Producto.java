package Integrador1;

public class Producto {
	private int id,facturado;
	private String nombre;
	private int valor;
	
	public Producto(String n, int v) {
		this.nombre= n;
		this.valor= v;
	}
	
	public Producto(int id, String n, int v) {
		this.id=id;
		this.nombre= n;
		this.valor= v;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id= id;
	}
	
	@Override
	public String toString() {
		return nombre+" Facturo= "+ this.facturado;
		
	}
	public void setFacturado(int f) {
		this.facturado=f;
	}
	

}
