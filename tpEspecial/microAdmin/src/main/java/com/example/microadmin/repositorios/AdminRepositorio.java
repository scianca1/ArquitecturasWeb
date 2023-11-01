package com.example.microadmin.repositorios;

import com.example.microadmin.entitys.Administrador;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepositorio extends RepositorioBase <Administrador, Long> {

    @Query("SELECT a FROM Administrador a")
    Optional<Administrador> getAdmin();

    @Query("UPDATE Administrador a SET a.tarifa = :tarifaNormal, a.tarifaPorPausaExtensa = :tarifaAumentada")
    Optional<Administrador> actualizarTarifas(int tarifaNormal, int tarifaAumentada);


}