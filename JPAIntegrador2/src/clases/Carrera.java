package clases;

import java.util.Set;

import javax.persistence.*;
import repositorios.EstudianteCarreraRepositorio;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Carrera {
   

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String nombre;
    @Column
    private Double duracionAnios;
    @OneToMany(mappedBy = "carrera")
    Set<EstudianteCarrera> estudiantes;
    
    public Carrera(String nombre, Double duracionAnios) {
        this.nombre = nombre;
        this.duracionAnios = duracionAnios;
        this.estudiantes = new HashSet<EstudianteCarrera>();
    }
    public Carrera(Integer id, String nombre, Double duracionAnios) {
        this.id = id;
        this.nombre = nombre;
        this.duracionAnios = duracionAnios;
        this.estudiantes = new HashSet<EstudianteCarrera>();
    }
    public Carrera() {
        super();
    } 
    public String getNombre() {
        return nombre;
    }
    public Double getDuracionAnios() {
        return duracionAnios;
    }
    public Integer getId() {
        return id;
    }
}