package com.example.microViaje.dtos;

import com.example.microViaje.entitys.Viaje;
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
    private Long idMonopatin;
    private Long idCuenta;
    private Long idParadaOrigen;
    private Long idParadaDestino;
    private int valorViaje;
    private Long kmRecorridos;
    private int pausa;

    public ViajeDto(LocalDate fechaInicio, LocalTime horaInicio, LocalDate fechaFin, LocalTime horaFin, Long idUsuario, Long idMonopatin, int pausa, Long idCuenta, Long idParadaOrigen, Long idParadaDestino, int valor, Long km) {
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
        this.kmRecorridos=km;
        this.pausa = pausa;
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
        this.kmRecorridos= viaje.getKmRecorridos();
        this.pausa = viaje.getPausa();
    }
}
