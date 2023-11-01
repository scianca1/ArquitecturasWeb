package com.example.microviaje.Repositories;

import com.example.microviaje.entitys.Viaje;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViajeRepository extends BaseRepository<Viaje, Integer> {

    @Query("SELECT v FROM Viaje v WHERE YEAR(v.fechaInicio) = :anio")
    public List<Viaje> viajeByAnio(Integer anio);
    @Query("SELECT v FROM Viaje v WHERE YEAR(v.fechaInicio) = :anio AND MONTH(v.fechaInicio) >= :mes1 AND MONTH(v.fechaInicio) <= :mes2")
    public List<Viaje> viajesPorAñoEntreMeses(Integer anio,Integer mes1, Integer mes2);

}
