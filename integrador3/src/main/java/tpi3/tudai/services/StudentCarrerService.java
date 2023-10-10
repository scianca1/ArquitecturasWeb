package tpi3.tudai.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.transaction.Transactional;
import tpi3.tudai.entities.Carrer;
import tpi3.tudai.entities.Student;
import tpi3.tudai.entities.StudentCarrer;
import tpi3.tudai.repositories.CarrerRepository;
import tpi3.tudai.repositories.StudentCarrerRepository;
import tpi3.tudai.dtos.CarrerDTO;
import tpi3.tudai.dtos.StudentCarrerDTO;
import tpi3.tudai.dtos.StudentDTO;
import tpi3.tudai.repositories.StudentRepository;
@Service
public class StudentCarrerService implements BaseService<StudentCarrerDTO>{

	private StudentCarrerRepository repository;
	private StudentRepository studentRepository;
	private CarrerRepository carrerRepository;
	
	public StudentCarrerService(StudentCarrerRepository repository, StudentRepository studentRepository,CarrerRepository carrerRepository) {
		this.repository = repository;
		this.studentRepository = studentRepository;
		this.carrerRepository = carrerRepository;
	}

	@Override
	@Transactional
	public List<StudentCarrerDTO> findAll() throws Exception {
		return repository.findAll().stream().map(StudentCarrerDTO::new).toList();
	}

	@Override
	public StudentCarrerDTO findById(Integer id) throws Exception {
		return null;
	}

	@Override
	public StudentCarrerDTO save(StudentCarrerDTO entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}

