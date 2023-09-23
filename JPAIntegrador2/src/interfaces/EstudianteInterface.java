package interfaces;

import clases.Estudiante;
import dtos.EstudianteDto;
import java.util.List;

public interface EstudianteInterface {
    public List<EstudianteDto> getEstudiantesDto();
    public EstudianteDto getEstudianteDtoById(Integer id);
    public Estudiante getEstudianteById(Integer id);
    public boolean removeEstudiante(Integer id);
    public void insertEstudiante(Estudiante e);
}