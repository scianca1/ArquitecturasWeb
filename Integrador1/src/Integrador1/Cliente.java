package Integrador1;

public class Cliente {
	private static int nextId= 0;
	private int id;
	private String nombre;
	private String email;
	
	public Cliente(int id, String n, String e) {
		this.id= id;
		this.nombre= n;
		this.email= e;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}
	
	

}
