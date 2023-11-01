package com.example.microadmin.repositorios;

import com.example.microadmin.entitys.Administrador;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepositorio extends RepositorioBase <Administrador, Integer> {

    @Query("SELECT a FROM Administrador a")
    Administrador getAdmin();

    @Query("UPDATE Administrador a SET a.tarifa = :tarifaNormal, a.tarifaPorPausaExtensa = :tarifaAumentada")
    Administrador actualizarTarifas(int tarifaNormal, int tarifaAumentada);

    @Query("SELECT a.tarifa, a.tarifaPorPausaExtensa FROM Administrador a")
    Administrador getTarifas();


}
// UPDATE Usuario u SET u.email = :#{#us.email}, u.nombre = :#{#us.nombre}, u.nombreDeUsuario = :#{#us.nombreDeUsuario}, u.telefono = :#{#us.telefono} WHERE u.id = :usuarioId"