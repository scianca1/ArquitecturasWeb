package Integrador1;

public class Producto {
	private static int nextId;
	private int id;
	private String nombre;
	private int valor;
	
	public Producto(String n, int v) {
		this.id= nextId++;
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
	

}
