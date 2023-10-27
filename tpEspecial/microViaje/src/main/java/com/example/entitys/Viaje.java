package com.example.entitys;

import com.example.dtos.UsuarioDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "viaje")
@Getter
@Setter
public class Viaje {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private Date fechaInicio;

    @Column
    private double horaInicio;

    @Column
    private Date fechaFin;

    @Column
    private double horaFin;

    @Column
    private Long idUsuario;

    @Column
    private Long idMonopatin;

    @Column
    private int pausa;

    public Viaje(){
    }

    public Viaje(Date fechaInicio, double horaInicio, Date fechaFin, double horaFin, Long idUsuario, Long idMonopatin, int pausa) {
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.fechaFin = fechaFin;
        this.horaFin = horaFin;
        this.idUsuario = idUsuario;
        this.idMonopatin = idMonopatin;
        this.pausa = pausa;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}


/*
    @ManyToMany
    private List<Cuenta> cuentas;

*/
