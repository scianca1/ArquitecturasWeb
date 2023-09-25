package dtos;

import clases.EstudianteCarrera;
import java.util.ArrayList;

public class EstudianteDto {
    @Override
	public String toString() {
		return "EstudianteDto [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}

	private Integer id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String genero;
    private String ciudadResidencia;
    private String documento;
    private Integer numeroLibreta;
    private ArrayList<EstudianteCarrera> carreras;

    public EstudianteDto(String nombre, String apellido, Integer edad, String genero, String ciudadResidencia, String documento, Integer numeroLibreta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudadResidencia = ciudadResidencia;
        this.documento = documento;
        this.numeroLibreta = numeroLibreta;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }


    public String getApellido() {
        return apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }


    public String getDocumento() {
        return documento;
    }

    public Integer getNumeroLibreta() {
        return numeroLibreta;
    }

    public ArrayList<EstudianteCarrera> getCarreras() {
        return carreras;
    }

}
