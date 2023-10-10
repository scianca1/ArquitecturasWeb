package tpi3.tudai.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import tpi3.tudai.dtos.StudentDTO;
import tpi3.tudai.entities.Student;
import tpi3.tudai.repositories.StudentRepository;

@Service
public class StudentService implements BaseService<StudentDTO>{
	
	@Autowired
	private StudentRepository repository;

	@Override
	@Transactional
	public List<StudentDTO> findAll() {
		return repository.findAll().stream().map(StudentDTO::new).toList();
	}

	public StudentDTO findById(Integer id){
		return repository.findById(id).map(StudentDTO::new).orElseThrow(() -> new IllegalArgumentException("ID invalido:" + id));
	}
	
    public List<StudentDTO> findByGenero(String genero) {
       return repository.findByGenero(genero).stream().map(StudentDTO::new ).toList();
    }
	
	@Transactional
    public StudentDTO findByNotebook(Integer libreta) {
        return repository.findByNotebook(libreta).map(StudentDTO::new).orElseThrow(() -> new IllegalArgumentException("Libreta de usuario invalida:" + libreta));
    }
	
	public List<StudentDTO> findAllOrderByName(){
		return repository.findAllOrderByName().stream().map(StudentDTO::new).toList();
	}
	
	public List<StudentDTO> findStudentsByCityAndCarrer(Integer id, String city){
		return repository.findStudentsByCityAndCarrer(id, city).stream().map(StudentDTO::new).toList();
	}
	
	@Override
	@Transactional
	public StudentDTO save(StudentDTO s) throws Exception {
		try {
			Student st= new Student(s);
			if(!this.repository.findById(s.getId()).isEmpty()) {
				throw new Exception("La ID del estudiante ya existe.");
			}
			if(!this.repository.findByNotebook(s.getNumeroLibreta()).isEmpty()) {
				throw new Exception("La libreta del estudiante ya existe.");
			}
			return new StudentDTO(repository.save(st));	
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}
	}


}
