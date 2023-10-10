
package tpi3.tudai.entities;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "studentsCarrers")
@Data
@IdClass(StudentCarrerID.class)
@Getter
@Setter
public class StudentCarrer { 
	
    @Column
    LocalDate inscripcion;
    
    @Column(nullable = true)
    LocalDate graduacion;
    
    @Column
    private boolean seGraduo; 
    
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id") 
    private Student estudiante;
    
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_carrera", referencedColumnName = "id") 
    private Carrer carrera;
    
    public StudentCarrer(LocalDate inscripcion, LocalDate graduacion, Student e, Carrer c) {
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
    
    public StudentCarrer(Student e, Carrer c) {
        this.inscripcion = LocalDate.now();
        this.graduacion=LocalDate.of(0, 1, 1);
        this.estudiante = e;
        this.carrera = c;
        this.seGraduo=false;

    }
   
    public StudentCarrer() {
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
    
    public Student getEstudiante() {
        return estudiante;
    }
    
    public Carrer getCarrera() {
        return carrera;
    }
    
    public Carrer getCarrer(Optional<Carrer> carrerOptional) {
        return carrerOptional.orElse(null);
    }
    
    public Student getStudent(Optional<Student> studentOptional) {
        return studentOptional.orElse(null);
    }

	public void setInscripcion(LocalDate inscripcion) {
		this.inscripcion = inscripcion;
	}

	public void setGraduacion(LocalDate graduacion) {
		if(graduacion.getYear()==0) {
        	this.seGraduo= false;
        }else {
        	this.seGraduo = true;
        }
		this.graduacion = graduacion;
	}

	public void setEstudiante(Student estudiante) {
		this.estudiante = estudiante;
	}

	public void setCarrera(Carrer carrera) {
		this.carrera = carrera;
	}
	
}
