package Integrador1;

public class Cliente {
	private static int nextId= 0;
	private int id;
	private String nombre;
	private String email;
	
	public Cliente(String n, String e) {
		this.id= nextId++;
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
	
	

}
