package com.example.microusuarios.entitys;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.microusuarios.dtos.UsuarioDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column
    private String nombre;
    @Column(unique = true, nullable = false)
    private String nombreDeUsuario;
    @Column
    private Long telefono;
    @Column(nullable=false)
    private String email;
    @Column(nullable=false)
    private String password;
    @Column
    private Long x;
    @Column
    private Long y;
    @ManyToMany
    @Column(nullable=false)
    private List<Cuenta> cuentas;
    @ManyToMany
    @Column(nullable=false)
    private List<Authority> authorities;

    public Usuario(String nombre, String nombreDeUsuario, Long telefono, String email) {
        this.nombre = nombre;
        this.nombreDeUsuario = nombreDeUsuario;
        this.telefono = telefono;
        this.email = email;
        this.cuentas = new ArrayList<>();
    }

    public Usuario() {
        this.cuentas = new ArrayList<>();
        this.authorities = new ArrayList<>();
    }

    public Usuario(Long id, String nombre, String nombreDeUsuario, Long telefono, String email) {
        this.id=id;
        this.nombre = nombre;
        this.nombreDeUsuario = nombreDeUsuario;
        this.telefono = telefono;
        this.email = email;
        this.cuentas = new ArrayList<>();
    }
    public Usuario(UsuarioDto u) {
        this.id=u.getId();
        this.nombre = u.getNombre();
        this.nombreDeUsuario = u.getNombreDeUsuario();
        this.telefono = u.getTelefono();
        this.email = u.getEmail();
//        this.cuentas = u.getCuentas().stream().map(Cuenta::new).toList();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.cuentas.toString();
    }

    public void addCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
    }

    public void removeCuenta(Cuenta c) {
        this.cuentas.remove(c);
    }
    public Long getX(){return this.x;}

    public Long getY(){return this.y;}
}


