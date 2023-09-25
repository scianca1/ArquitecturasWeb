package clases;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class EstudianteCarrera implements Serializable {
   

    @Column
    LocalDate inscripcion;
    @Column(nullable = true)
    LocalDate graduacion;
    @Column
    private boolean seGraduo;
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "estudiante")
    private Estudiante estudiante;
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "carrera")
    private Carrera carrera;
    
    public EstudianteCarrera() {
    }
    public EstudianteCarrera(LocalDate inscripcion, Estudiante e, Carrera c) {
        this.inscripcion = inscripcion;
        this.seGraduo = false;
        this.graduacion = null;
        this.estudiante = e;
        this.carrera = c;
    }
    public EstudianteCarrera(Estudiante e, Carrera c){
        this.carrera = c;
        this.estudiante = e;
        this.inscripcion = LocalDate.now();
        this.graduacion = null;
        this.seGraduo = false;
    }
    public EstudianteCarrera(LocalDate inscripcion, LocalDate graduacion, Estudiante e, Carrera c) {
        this.inscripcion = inscripcion;
        if(graduacion.getYear()==0) {
        	this.seGraduo= false;
        }else {
        	this.seGraduo = true;
        }
        this.graduacion = graduacion;
        this.estudiante = e;
        this.carrera = c;
    }
    public LocalDate getInscripcion() {
        return inscripcion;
    }
    public LocalDate getGraduacion() {
        return graduacion;
    }
    public boolean isSeGraduo() {
        return seGraduo;
    }
    public Estudiante getEstudiante() {
        return estudiante;
    }
    public Carrera getCarrera() {
        return carrera;
    }

}
