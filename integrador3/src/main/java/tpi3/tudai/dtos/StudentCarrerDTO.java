package tpi3.tudai.dtos;

import lombok.Getter;
import lombok.Setter;
import tpi3.tudai.entities.StudentCarrer;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class StudentCarrerDTO {
    private Integer id_student;
    private Integer id_carrer;

    public StudentCarrerDTO(){

    }
    
    public StudentCarrerDTO(Integer id_student, Integer id_carrer){
       this.id_student= id_student;
       this.id_carrer= id_carrer;
    }

    public StudentCarrerDTO(StudentCarrer sc){
    	this.id_student= sc.getEstudiante().getId();
    	this.id_carrer= sc.getCarrera().getId();
    }
    
	public Integer getId_student() {
		return id_student;
	}
	
	public Integer getId_carrer() {
		return id_carrer;
	}
	
	public void setId_Estudiante(Integer id_student) {
		this.id_student = id_student;
	}
	
	public void setId_Carrera(Integer id_carrer) {
		this.id_carrer = id_carrer;
	}
	
}
