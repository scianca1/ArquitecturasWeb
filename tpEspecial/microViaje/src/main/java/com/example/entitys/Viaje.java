package com.example.entitys;

import com.example.dtos.UsuarioDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
@Entity
@Table(name = "viaje")
@Getter
@Setter
public class Viaje {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private LocalDate fechaInicio;

    @Column
    private LocalTime horaInicio;

    @Column
    private LocalDate fechaFin;

    @Column
    private LocalTime horaFin;

    @Column
    private Long idUsuario;

    @Column
    private Long idMonopatin;

    @Column
    private Long idCuenta;

    @Column
    private int pausa;

    @Column
    private Long idParadaOrigen;

    @Column
    private Long idParadaDestino;

    @Column
    private int valorViaje;

    public Viaje(){
    }

    public Viaje(Long idUsuario, Long idMonopatin, Long idCuenta, Long origen, Long destino){
        this.idUsuario=idUsuario;
        this.idMonopatin=idMonopatin;
        this.idCuenta=idCuenta;
        this.idParadaOrigen=origen;
        this.idParadaDestino=destino;
        this.fechaInicio= LocalDate.now();
        this.fechaFin=null;
        this.horaInicio= LocalTime.now();
        this.horaFin= null;
        this.pausa=15;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}


