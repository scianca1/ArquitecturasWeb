package com.example.microusuarios.repositorios;

import com.example.microusuarios.entitys.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public interface UsuarioRepositorio extends RepositorioBase<Usuario, Integer> {

    @Query("UPDATE Usuario u SET u.email = :#{#us.email}, u.nombre = :#{#us.nombre}, u.nombreDeUsuario = :#{#us.nombreDeUsuario}, u.telefono = :#{#us.telefono}  WHERE u.id = :usuarioId")
    @Modifying
    @Transactional
    public void put(@Param("us") Usuario usuario, @Param("usuarioId") Long usuarioId);
}


