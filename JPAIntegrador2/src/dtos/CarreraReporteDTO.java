package dtos;

public class CarreraReporteDTO {

	private String nombre;
	private Integer anio;
	private Integer CantEgresados;
	private Integer Cantinscriptos;
	
	public CarreraReporteDTO(String nombre,Integer anio, Integer CantEgresados,Integer Cantinscriptos) {
		this.nombre=nombre;
		this.anio=anio;
		this.CantEgresados=CantEgresados;
		this.Cantinscriptos=Cantinscriptos;
	}

	@Override
	public String toString() {
		return "CarreraReporteDTO [nombre=" + nombre + ", anio=" + anio + ", CantEgresados=" + CantEgresados
				+ ", Cantinscriptos=" + Cantinscriptos + "]";
	}
	
}
