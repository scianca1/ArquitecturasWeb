package clases;

import javax.persistence.*;
import repositorios.EstudianteCarreraRepositorio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Estudiante {
    
//    @GeneratedValue(strategy = GenerationType.AUTO)
	@Id
    private Integer id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private Integer edad;
    @Column
    private String genero;
    @Column
    private String ciudadResidencia;
    @Column
    private String documento;
    @Column
    private Integer numeroLibreta;
    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY)
    private Set<EstudianteCarrera> carreras;
    
    public Estudiante(String nombre, String apellido, Integer edad, String genero, String ciudadResidencia, String documento, Integer numeroLibreta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudadResidencia = ciudadResidencia;
        this.documento = documento;
        this.numeroLibreta = numeroLibreta;
        this.carreras = new HashSet<EstudianteCarrera>();
    }
    public Estudiante(Integer id, String nombre, String apellido, Integer edad, String genero, String ciudadResidencia, String documento, Integer numeroLibreta) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudadResidencia = ciudadResidencia;
        this.documento = documento;
        this.numeroLibreta = numeroLibreta;
        this.carreras = new HashSet<EstudianteCarrera>();
    }
    public Estudiante() {
        this.carreras = new HashSet<EstudianteCarrera>();
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
    public ArrayList<EstudianteCarrera> getCarreras(){
        if(this.carreras.isEmpty()){
            EstudianteCarreraRepositorio ecr = new EstudianteCarreraRepositorio();
            List<EstudianteCarrera> aux = ecr.getEstudianteCarreraByIdEstudiante(this.getId());
            return new ArrayList<EstudianteCarrera>(aux);
        }
        return new ArrayList<EstudianteCarrera>(this.carreras);
    }
    public String getNombre(){
        return this.nombre;
    }
    public Integer getId() {
        return id;
    }
	@Override
	public String toString() {
		return "Estudiante [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}
}
