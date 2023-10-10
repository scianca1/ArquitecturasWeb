package tpi3.tudai.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import tpi3.tudai.entities.Carrer;
import tpi3.tudai.entities.Student;
import tpi3.tudai.entities.StudentCarrer;
import tpi3.tudai.repositories.CarrerRepository;
import tpi3.tudai.repositories.StudentCarrerRepository; 
import tpi3.tudai.repositories.StudentRepository;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Component
public class DataUpload {

	private final CarrerRepository carrerRepository;
    private final StudentRepository studentRepository;
    private final StudentCarrerRepository studentCarrerRepository;

    @Autowired
    public DataUpload(StudentRepository studentRepository, CarrerRepository carrerRepository, StudentCarrerRepository studentCarrerRepository) {
        this.studentRepository = studentRepository;
        this.carrerRepository= carrerRepository;
        this.studentCarrerRepository= studentCarrerRepository;
    }

    public void loadDataFromCSV() throws IOException {
        File studentsCSV = ResourceUtils.getFile("src/main/java/tpi3/tudai/csv/estudiantes.csv");
        try (FileReader reader = new FileReader(studentsCSV); 
        	CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
            for (CSVRecord csvRecord : csvParser) {
                Student student = new Student();
                student.setNombre(csvRecord.get("nombre"));
                student.setApellido(csvRecord.get("apellido"));
                student.setEdad(Integer.parseInt(csvRecord.get("edad")));
                student.setGenero(csvRecord.get("genero"));
                student.setCiudadResidencia(csvRecord.get("ciudad"));
                student.setDocumento(csvRecord.get("DNI"));
                student.setNumeroLibreta(Integer.parseInt(csvRecord.get("LU")));
                student.setId(Integer.parseInt(csvRecord.get("id")));
                studentRepository.save(student); 
            }
        }
        File carrersCSV = ResourceUtils.getFile("src/main/java/tpi3/tudai/csv/carreras.csv");
        try (FileReader reader = new FileReader(carrersCSV); 
        	CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
            for (CSVRecord csvRecord : csvParser) {
                Carrer carrer = new Carrer();
                carrer.setId(Integer.parseInt(csvRecord.get("id_carrera")));
                carrer.setNombre(csvRecord.get("carrera"));
                carrer.setDuracionAnios(Integer.parseInt(csvRecord.get("duracion")));
                carrerRepository.save(carrer); 
            }
        }
        
        File studentsCarrersCSV = ResourceUtils.getFile("src/main/java/tpi3/tudai/csv/estudianteCarrera.csv");
        try (FileReader reader = new FileReader(studentsCarrersCSV);
        	CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
            for (CSVRecord csvRecord : csvParser) {
                StudentCarrer studentCarrer = new StudentCarrer();
                Integer idCarrer= Integer.parseInt(csvRecord.get("id_carrera"));
                Integer idStudent= Integer.parseInt(csvRecord.get("id_estudiante"));
                Optional<Carrer> carrerOptional= carrerRepository.findById(idCarrer);
                Optional<Student> studentOptional= studentRepository.findById(idStudent);
                System.out.println(carrerOptional.get().getNombre());
                System.out.println(studentOptional.get().getNombre());

                Carrer carrer= studentCarrer.getCarrer(carrerOptional);
                Student student= studentCarrer.getStudent(studentOptional);
                LocalDate inscripcion =  LocalDate.of(Integer.parseInt(csvRecord.get("inscripcion")),1,1) ;
                LocalDate graduacion =  LocalDate.of(Integer.parseInt(csvRecord.get("graduacion")),1,1) ;
                studentCarrer.setCarrera(carrer);
                studentCarrer.setEstudiante(student);
                studentCarrer.setInscripcion(inscripcion);
                studentCarrer.setGraduacion(graduacion);
                studentCarrerRepository.save(studentCarrer);
            }
        }
    }

}