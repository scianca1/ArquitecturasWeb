package interfaces;


import clases.Carrera;
import dtos.CarreraDto;

import java.util.Collection;
import java.util.List;

public interface CarreraInterface {
    public List<CarreraDto> getCarrerasDto();
    public CarreraDto getCarreraDtoById(Integer id);
    public Carrera getCarreraById(Integer id);
    public boolean removeCarrera(Integer id);
    public void insertCarrera(Carrera c);
}
