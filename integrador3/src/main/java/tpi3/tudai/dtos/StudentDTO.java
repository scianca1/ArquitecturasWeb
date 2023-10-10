package tpi3.tudai.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import tpi3.tudai.entities.Student;
import tpi3.tudai.entities.StudentCarrer;

@Getter
@Setter
public class StudentDTO {

	private Integer id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String genero;
    private String ciudadResidencia;
    private String documento;
    private Integer numeroLibreta;
    private Set<StudentCarrer> carreras;

    public StudentDTO(){
        super();
    }
    
    public StudentDTO(Student s) {
    	this.id = s.getId();
        this.nombre = s.getNombre();
        this.apellido = s.getApellido();
        this.edad = s.getEdad();
        this.genero = s.getGenero();
        this.ciudadResidencia = s.getCiudadResidencia();
        this.documento = s.getDocumento();
        this.numeroLibreta = s.getNumeroLibreta();
        this.carreras= new HashSet<>();

    }
    
    public StudentDTO(Integer id, String nombre, String apellido, Integer edad, String genero, String ciudadResidencia, String documento, Integer numeroLibreta) {
          this.id=id;
    	  this.nombre = nombre;
          this.apellido = apellido;
          this.edad = edad;
          this.genero = genero;
          this.ciudadResidencia = ciudadResidencia;
          this.documento = documento;
          this.numeroLibreta = numeroLibreta;
    }

    public Integer getId() {
        return id;    }

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

    public Set<StudentCarrer> getCarreras() {
        return carreras;
    }

    @Override
    public String toString() {
    	return "EstudianteDto [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + "]";
    }
}
