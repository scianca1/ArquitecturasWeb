package tpi3.tudai.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.transaction.Transactional;
import tpi3.tudai.dtos.CarrerDTO;
import tpi3.tudai.dtos.ReportCarrerDTO;
import tpi3.tudai.dtos.StudentCarrerDTO;
import tpi3.tudai.dtos.StudentDTO;
import tpi3.tudai.entities.Carrer;
import tpi3.tudai.entities.Student;
import tpi3.tudai.entities.StudentCarrer;
import tpi3.tudai.repositories.CarrerRepository;
import tpi3.tudai.repositories.ReportResponse;
import tpi3.tudai.repositories.StudentCarrerRepository;
import tpi3.tudai.repositories.StudentRepository;
import tpi3.tudai.services.exception.NotFoundException;

@Service
public class CarrerService implements BaseService<CarrerDTO>{
	
	private CarrerRepository repository;
	private StudentCarrerRepository studentCarrerRepository;
	private StudentRepository studentRepository;
	
	@Autowired
	public CarrerService(CarrerRepository repository, StudentCarrerRepository studentCarrerRepository, StudentRepository studentRepository) {
		this.repository = repository;
		this.studentCarrerRepository = studentCarrerRepository;
		this.studentRepository = studentRepository;
	}

	@Override
	@Transactional
	public List<CarrerDTO> findAll() throws Exception {
		return repository.findAll().stream().map(CarrerDTO::new ).toList();
	}

	@Override
	@Transactional
	public CarrerDTO findById(Integer id) throws Exception{
		return repository.findById(id).map(CarrerDTO::new).orElseThrow(
	            () -> new IllegalArgumentException("ID invalido:" + id));
	}
	
	@Transactional
	public List<CarrerDTO> findCarrersOrderByRegistered() throws Exception{
		try {
			List<CarrerDTO> carrers= repository.findCarrersOrderByRegistered();
			return carrers;
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}	
	}
	
	@Transactional
	public StudentCarrerDTO matricular(Integer id_student, Integer id_carrer){
		final var carrer = repository.findById(id_carrer).orElseThrow(() -> new NotFoundException("Carrera no existe"));
		final var student= studentRepository.findById(id_student).orElseThrow(() -> new NotFoundException("Estudiante no existe"));
		StudentCarrer sc= new StudentCarrer(student, carrer);
		return new StudentCarrerDTO(this.studentCarrerRepository.save(sc));
	}
	
	@Transactional
	public List<ReportCarrerDTO> getReport() {
		List<ReportResponse> rta= repository.getReport();
	    return rta.stream().map(r-> new ReportCarrerDTO(r.getCarrera(), r.getCantidadInscriptos(), r.getCantidadEgresados(), r.getAnio() ) ).toList();
	}
	
	@Override
	@Transactional
	public CarrerDTO save(CarrerDTO cdto) throws Exception {
		try {
			Carrer c= new Carrer(cdto);
			return new CarrerDTO(repository.save(c));
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}
	}
	
}
