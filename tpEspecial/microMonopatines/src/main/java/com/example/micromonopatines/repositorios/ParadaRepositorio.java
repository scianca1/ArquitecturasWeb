package com.example.micromonopatines.repositorios;

import com.example.micromonopatines.entitys.Monopatin;
import com.example.micromonopatines.entitys.Parada;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParadaRepositorio extends  RepositorioBase<Parada, Long>{
    @Query("UPDATE Parada p SET p.x = :#{#parada.x}, p.y = :#{#parada.y} WHERE p.id = :paradaId")
    @Modifying
    @Transactional
    public void put(@Param("parada") Parada parada, @Param("paradaId") Long paradaId);
}
