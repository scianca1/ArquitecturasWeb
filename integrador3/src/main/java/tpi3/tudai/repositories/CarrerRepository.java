package tpi3.tudai.repositories;

import java.sql.ResultSet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tpi3.tudai.dtos.CarrerDTO;
import tpi3.tudai.dtos.ReportCarrerDTO;
import tpi3.tudai.entities.Carrer;


public interface CarrerRepository extends RepositoryBase<Carrer, Integer> {
	
	@Query("SELECT c FROM Carrer c WHERE c.nombre = :nombre")
	public List<Carrer> findAllByName(String nombre);
	
	@Query("SELECT new tpi3.tudai.dtos.CarrerDTO(c.id, c.nombre, c.duracionAnios, COUNT(sc.estudiante.id)) FROM Carrer c JOIN StudentCarrer sc ON (c.id=sc.carrera.id) GROUP BY sc.carrera.id, c.nombre, c.duracionAnios ORDER BY COUNT(sc.estudiante.id) DESC")
	public List<CarrerDTO> findCarrersOrderByRegistered();

	@Query(value= "SELECT carrera, SUM(cant_inscriptos) AS cantidadInscriptos,SUM(cant_graduados) AS cantidadEgresados, anio "
			+ "    FROM (SELECT c.nombre AS carrera, COUNT(ec.estudiante_id) AS cant_inscriptos, 0 AS cant_graduados,YEAR(ec.inscripcion) AS anio "
			+ "        FROM students_carrers ec JOIN carrers c ON ec.carrera_id = c.id "
			+ "        GROUP BY c.nombre, ec.carrera_id, anio "
			+ "    UNION ALL "
			+ "        SELECT c.nombre AS carrera, 0 AS cant_inscriptos, COUNT(ec.estudiante_id) AS cant_graduados,YEAR(ec.graduacion) AS anio "
			+ "            FROM students_carrers ec JOIN carrers c ON ec.carrera_id = c.id "
			+ "            GROUP BY c.nombre, ec.carrera_id, anio "
			+ "    ) AS subQuery WHERE anio not like 1 GROUP BY carrera, anio ORDER BY carrera ASC,anio ASC", nativeQuery = true)
	public List<ReportResponse> getReport();
}
