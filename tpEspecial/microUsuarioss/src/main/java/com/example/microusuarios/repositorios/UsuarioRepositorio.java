package com.example.microusuarios.repositorios;

import com.example.microusuarios.entitys.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepositorio extends RepositorioBase<Usuario, Long> {

//    @Query("UPDATE Usuario u SET u.email = :#{#us.email}, u.nombre = :#{#us.nombre}, u.nombreDeUsuario = :#{#us.nombreDeUsuario}, u.telefono = :#{#us.telefono}  WHERE u.id = :usuarioId")
//    @Modifying
//    @Transactional
//    public void put(@Param("us") Usuario usuario, @Param("usuarioId") Long usuarioId);

    Optional<Usuario> findUserByEmailIgnoreCase(String email);

    boolean existsUsersByEmailIgnoreCase(String email);
}


