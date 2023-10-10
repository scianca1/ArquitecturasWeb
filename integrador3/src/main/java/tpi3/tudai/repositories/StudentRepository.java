package tpi3.tudai.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tpi3.tudai.entities.Student;

@Repository("StudentRepository")
public interface StudentRepository extends RepositoryBase<Student, Integer>  {
	
	@Query("SELECT s FROM Student s WHERE s.nombre = :nombre")
	public List<Student> findAllByName(String nombre);
	
	@Query("SELECT s FROM Student s WHERE s.apellido = :apellido")
	public List<Student> findAllBySurname(String apellido);
	
	@Query("SELECT s FROM Student s WHERE s.genero= :genre")
	public List<Student> findByGenero(String genre);
	
	@Query("SELECT s FROM Student s WHERE s.numeroLibreta= :notebook")
	public Optional<Student> findByNotebook(Integer notebook);
	
	@Query("SELECT s FROM Student s ORDER BY s.nombre ASC")
	public List<Student> findAllOrderByName();

	@Query("SELECT s FROM Student s JOIN StudentCarrer sc ON (s.id = sc.estudiante.id) WHERE sc.carrera.id= :id AND s.ciudadResidencia= :city")
	public List<Student> findStudentsByCityAndCarrer(Integer id, String city);
}

