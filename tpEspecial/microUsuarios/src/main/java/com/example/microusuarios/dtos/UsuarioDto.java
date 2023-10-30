package com.example.microusuarios.dtos;

import com.example.microusuarios.entitys.Cuenta;
import com.example.microusuarios.entitys.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UsuarioDto {
    private Long id;
    private String nombre;

    private String nombreDeUsuario;

    private Long telefono;

    private String email;

    private List<CuentaDto> cuentas;
    public UsuarioDto() {
        super();
    }
    public UsuarioDto(Long id, String nombre) {
        this.nombre = nombre;
        this.id=id;
    }

    public UsuarioDto(Long id, String nombre, String nombreDeUsuario, Long telefono, String email, List<Cuenta> cuentas) {
        this.id = id;
        this.nombre = nombre;
        this.nombreDeUsuario = nombreDeUsuario;
        this.telefono = telefono;
        this.email = email;
        ArrayList<CuentaDto> aux=new ArrayList<CuentaDto>();
        for(Cuenta c:cuentas){
            aux.add(new CuentaDto(c));
        }
        this.cuentas = aux;
    }

    public UsuarioDto(String nombre, String nombreDeUsuario, Long telefono, String email) {

        this.nombre = nombre;
        this.nombreDeUsuario = nombreDeUsuario;
        this.telefono = telefono;
        this.email = email;

    }

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.nombreDeUsuario = usuario.getNombreDeUsuario();
        this.telefono = usuario.getTelefono();
        this.email = usuario.getEmail();
        this.cuentas= new ArrayList<CuentaDto>();

    }

}
