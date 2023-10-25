package com.example.micromonopatines.dtos;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioDto {
    private Long id;
    private String nombre;

    private String nombreDeUsuario;

    private Long telefono;

    private String email;

    public UsuarioDto() {
        super();
    }
    public UsuarioDto(Long id, String nombre) {
        this.nombre = nombre;
        this.id=id;
    }

    public UsuarioDto(Long id, String nombre, String nombreDeUsuario, Long telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.nombreDeUsuario = nombreDeUsuario;
        this.telefono = telefono;
        this.email = email;
    }

    public UsuarioDto(String nombre, String nombreDeUsuario, Long telefono, String email) {

        this.nombre = nombre;
        this.nombreDeUsuario = nombreDeUsuario;
        this.telefono = telefono;
        this.email = email;

    }
}

