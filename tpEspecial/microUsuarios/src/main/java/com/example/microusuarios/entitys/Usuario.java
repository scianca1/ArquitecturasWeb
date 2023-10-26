package com.example.microusuarios.entitys;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Column
    private String nombreDeUsuario;
    @Column
    private Long telefono;
    @Column
    private String email;
    @ManyToMany
    private List<Cuenta> cuentas;

    public Usuario(String nombre, String nombreDeUsuario, Long telefono, String email) {
        this.nombre = nombre;
        this.nombreDeUsuario = nombreDeUsuario;
        this.telefono = telefono;
        this.email = email;
        this.cuentas = new ArrayList<Cuenta>();
    }

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String nombreDeUsuario, Long telefono, String email) {
        this.id=id;
        this.nombre = nombre;
        this.nombreDeUsuario = nombreDeUsuario;
        this.telefono = telefono;
        this.email = email;
        this.cuentas = new ArrayList<Cuenta>();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
