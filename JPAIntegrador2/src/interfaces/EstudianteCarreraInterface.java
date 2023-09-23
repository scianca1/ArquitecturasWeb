package interfaces;

import clases.EstudianteCarrera;
import java.util.List;

public interface EstudianteCarreraInterface {
    public void insertEstudianteCarrera(EstudianteCarrera c);
    public List<EstudianteCarrera> getEstudianteCarreraByIdEstudiante(Integer idEstudiante);

}

