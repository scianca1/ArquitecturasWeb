package com.example.microviaje.dtos;

import com.example.microviaje.entitys.Viaje;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ViajeDtoId extends ViajeDto{
    private Long id;

    public ViajeDtoId(Viaje viaje){
        super(viaje);
        this.id= viaje.getId();
    }

    public ViajeDtoId( LocalDate fechaInicio, LocalTime horaInicio, LocalDate fechaFin, LocalTime horaFin, Long idUsuario, String idMonopatin, int pausa, Long idCuenta, String idParadaOrigen, String idParadaDestino, boolean pausado,int valor,  LocalTime horaInicioPausa,LocalTime horaFinPausa) {
        super(fechaInicio,horaInicio,fechaFin,horaFin,idUsuario,idMonopatin,pausa,idCuenta,idParadaOrigen,idParadaDestino,pausado,valor,horaInicioPausa, horaFinPausa);
        this.id = id;
    }


}
