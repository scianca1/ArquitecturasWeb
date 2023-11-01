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

    public ViajeDtoId( LocalDate fechaInicio, LocalTime horaInicio, LocalDate fechaFin, LocalTime horaFin, Long idUsuario, Long idMonopatin, int pausa, Long idCuenta, Long idParadaOrigen, Long idParadaDestino, int valor) {
        super(fechaInicio,horaInicio,fechaFin,horaFin,idUsuario,idMonopatin,pausa,idCuenta,idParadaOrigen,idParadaDestino,valor);
        this.id = id;
    }


}
