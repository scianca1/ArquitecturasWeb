package com.example.micromonopatines.repositorios;

import com.example.micromonopatines.entitys.Monopatin;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MonopatinRepositorio extends RepositorioBase<Monopatin, Long> {
    @Query("UPDATE Monopatin m SET m.x = :#{#mono.x}, m.y = :#{#mono.y}, m.habilitado = :#{#mono.habilitado}, m.kmRecorridos = :#{#mono.kmRecorridos}, m.tiempoDeUso = :#{#mono.tiempoDeUso}, m.tiempoDeUsoConPausa = :#{#mono.tiempoDeUsoConPausa} WHERE m.id = :monoId")
    @Modifying
    @Transactional
    public void put(@Param("mono") Monopatin monopatin, @Param("monoId") Long monopatinId);
}