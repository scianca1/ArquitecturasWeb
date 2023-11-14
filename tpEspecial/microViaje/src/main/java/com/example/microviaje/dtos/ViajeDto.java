package com.example.microviaje.dtos;

import com.example.microviaje.entitys.Viaje;
import jakarta.persistence.Column;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ViajeDto {
    private LocalDate fechaInicio;
    private LocalTime horaInicio;
    private LocalDate fechaFin;
    private LocalTime horaFin;
    private Long idUsuario;
    private String idMonopatin;
    private Long idCuenta;
    private String idParadaOrigen;
    private String idParadaDestino;
    private boolean viajePausado;
    private int valorViaje;
    private int pausa;
    private LocalTime horaInicioPausa;
    private LocalTime horaFinPausa;

    public ViajeDto(){}

    public ViajeDto(LocalDate fechaInicio, LocalTime horaInicio, LocalDate fechaFin, LocalTime horaFin, Long idUsuario, String idMonopatin, int pausa, Long idCuenta, String idParadaOrigen, String idParadaDestino, boolean pausado, int valor, LocalTime horaInicioPausa, LocalTime horaFinPausa) {
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.fechaFin = fechaFin;
        this.horaFin = horaFin;
        this.idUsuario = idUsuario;
        this.idMonopatin = idMonopatin;
        this.idCuenta=idCuenta;
        this.idParadaOrigen=idParadaOrigen;
        this.idParadaDestino=idParadaDestino;
        this.valorViaje=valor;
        this.pausa = pausa;
        this.viajePausado= pausado;
        this.horaInicioPausa=horaInicioPausa;
        this.horaFinPausa=horaFinPausa;
    }
    public ViajeDto( Long idUsuario, String idMonopatin, Long idCuenta, String idParadaOrigen, String idParadaDestino) {
        this.idUsuario = idUsuario;
        this.idMonopatin = idMonopatin;
        this.idCuenta=idCuenta;
        this.idParadaOrigen=idParadaOrigen;
        this.idParadaDestino=idParadaDestino;
    }

    public ViajeDto(Viaje viaje){
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
        this.viajePausado=viaje.isViajePausado();
        this.horaInicioPausa=viaje.getHoraInicioPausa();
        this.horaFinPausa=viaje.getHoraFinPausa();
    }
}
