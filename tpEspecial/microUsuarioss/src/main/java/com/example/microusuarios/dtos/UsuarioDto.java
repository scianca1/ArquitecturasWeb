package com.example.microusuarios.dtos;

import com.example.microusuarios.entitys.Authority;
import com.example.microusuarios.entitys.Cuenta;
import com.example.microusuarios.entitys.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioDto {
    private Long id;
    private String nombre;
    private String password;
    private String nombreDeUsuario;
    private Long telefono;
    private String email;
    private List<Long> cuentas;
    private List<String> authorities;
    public UsuarioDto() {
        this.authorities = new ArrayList<>();
        this.cuentas = new ArrayList<>();
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
//        List<CuentaDto> aux=new ArrayList<>();
//        for(Cuenta c:cuentas){
//            aux.add(new CuentaDto(c));
//        }
//        this.cuentas = aux;
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
        this.password = usuario.getPassword();
//        this.cuentas= usuario.getCuentas().stream().map(CuentaDto::new).toList();

    }

}
