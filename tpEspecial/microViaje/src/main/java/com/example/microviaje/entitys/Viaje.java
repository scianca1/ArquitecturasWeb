package com.example.microviaje.entitys;

import com.example.microviaje.dtos.ViajeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
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
    private String idMonopatin;

    @Column
    private Long idCuenta;

    @Column
    private int pausa;

    @Column
    private String idParadaOrigen;

    @Column
    private String idParadaDestino;

    @Column
    private int valorViaje;

    @Column
    private boolean viajePausado;

    @Column
    private LocalTime horaInicioPausa;

    @Column
    private LocalTime horaFinPausa;

    public Viaje(){
    }

    public Viaje(Long idUsuario, String idMonopatin, Long idCuenta, String origen, String destino){
        this.idUsuario=idUsuario;
        this.idMonopatin=idMonopatin;
        this.idCuenta=idCuenta;
        this.idParadaOrigen=origen;
        this.idParadaDestino=destino;
        this.fechaInicio= LocalDate.now();
        this.fechaFin=null;
        this.horaInicio= LocalTime.now();
        this.horaFin= null;
        this.valorViaje=0;
        this.pausa=15;
        this.viajePausado=false;
        this.horaInicioPausa=null;
        this.horaFinPausa=null;

    }

    public Viaje(ViajeDto viaje){
        this.fechaInicio = viaje.getFechaInicio();
        this.horaInicio = viaje.getHoraInicio();
        this.fechaFin = viaje.getFechaFin();
        this.horaFin = viaje.getHoraFin();
        this.idUsuario = viaje.getIdUsuario();
        this.idMonopatin = viaje.getIdMonopatin();
        this.idCuenta= viaje.getIdCuenta();
        this.idParadaOrigen=viaje.getIdParadaOrigen();
        this.idParadaDestino=viaje.getIdParadaDestino();
        this.valorViaje= viaje.getValorViaje();
        this.pausa = viaje.getPausa();
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}


