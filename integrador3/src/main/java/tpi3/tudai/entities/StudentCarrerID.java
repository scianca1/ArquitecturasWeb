package tpi3.tudai.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable
public class StudentCarrerID implements Serializable {
    
    @ManyToOne
    private Student estudiante;

    @ManyToOne
    private Carrer carrera;
    
	public StudentCarrerID(Student estudiante, Carrer carrera) {
		super();
		this.estudiante = estudiante;
		this.carrera = carrera;
	}

	public StudentCarrerID() {
		super();
	}

	public Student getEstudiante() {
		return estudiante;
	}

	public void setStudent(Student estudiante) {
		this.estudiante = estudiante;
	}

	public Carrer getCarrera() {
		return carrera;
	}

	public void setCarrer(Carrer carrera) {
		this.carrera = carrera;
	}
	
	@Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        StudentCarrerID id = (StudentCarrerID) o;
        return Objects.equals(estudiante, id.estudiante) && Objects.equals(carrera, id.carrera);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estudiante, carrera);
    }

    
}
