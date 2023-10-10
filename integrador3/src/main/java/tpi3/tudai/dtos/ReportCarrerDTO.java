package tpi3.tudai.dtos;

import lombok.Getter;

@Getter
public class ReportCarrerDTO {

	private String nombre;
	private Integer anio;
	private Integer CantEgresados;
	private Integer CantInscriptos;
	
	public ReportCarrerDTO(String nombre, Integer Cantinscriptos, Integer CantEgresados, Integer anio ) {
		this.nombre=nombre;
		this.anio=anio;
		this.CantEgresados=CantEgresados;
		this.CantInscriptos=Cantinscriptos;
	}

	@Override
	public String toString() {
		return "CarreraReporteDTO [nombre=" + nombre + ", anio=" + anio + ", CantEgresados=" + CantEgresados
				+ ", Cantinscriptos=" + CantInscriptos + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getCantEgresados() {
		return CantEgresados;
	}

	public void setCantEgresados(Integer cantEgresados) {
		CantEgresados = cantEgresados;
	}

	public Integer getCantInscriptos() {
		return CantInscriptos;
	}

	public void setCantinscriptos(Integer cantinscriptos) {
		CantInscriptos = cantinscriptos;
	}
	
	
	
}
