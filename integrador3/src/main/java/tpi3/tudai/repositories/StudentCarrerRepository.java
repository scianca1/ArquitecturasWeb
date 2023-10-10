package tpi3.tudai.repositories;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tpi3.tudai.entities.Carrer;
import tpi3.tudai.entities.Student;
import tpi3.tudai.entities.StudentCarrer;
import tpi3.tudai.entities.StudentCarrerID;

public interface StudentCarrerRepository extends RepositoryBase<StudentCarrer, Integer> {

	@Query(value= "SELECT carrera, SUM(cant_inscriptos) AS cant_inscriptos,SUM(cant_graduados) cant_graduados, anio FROM (SELECT c.nombre AS carrera, COUNT(ec.estudiante) AS cant_inscriptos, 0 AS cant_graduados,YEAR(ec.inscripcion) AS anio FROM estudiantecarrera ec JOIN carrera c ON ec.carrera = c.id GROUP BY c.nombre, ec.carrera, anio UNION ALL SELECT c.nombre AS carrera, 0 AS cant_inscriptos, COUNT(ec.estudiante) AS cant_graduados,YEAR(ec.graduacion) AS anio FROM estudiantecarrera ec JOIN carrera c ON ec.carrera = c.id GROUP BY c.nombre, ec.carrera, anio) AS subQuery WHERE anio not like 1 GROUP BY carrera, anio ORDER BY carrera ASC,anio ASC;", nativeQuery=true)
	public ResultSet getReport();
	
}