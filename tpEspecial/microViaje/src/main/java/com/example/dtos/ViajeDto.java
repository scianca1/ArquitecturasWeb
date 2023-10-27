package com.example.dtos;

import com.example.entitys.Viaje;
import jakarta.persistence.Column;
import lombok.Getter;

import java.util.Date;

@Getter
public class ViajeDto {
    private Date fechaInicio;
    private double horaInicio;
    private Date fechaFin;
    private double horaFin;
    private Long idUsuario;
    private Long idMonopatin;
    private int pausa;

    public ViajeDto(Date fechaInicio, double horaInicio, Date fechaFin, double horaFin, Long idUsuario, Long idMonopatin, int pausa) {
        this.fechaInicio = fechaInicio;
        this.horaInicio = horaInicio;
        this.fechaFin = fechaFin;
        this.horaFin = horaFin;
        this.idUsuario = idUsuario;
        this.idMonopatin = idMonopatin;
        this.pausa = pausa;
    }

    public ViajeDto(Viaje viaje){
        this.fechaInicio = viaje.getFechaInicio();
        this.horaInicio = viaje.getHoraInicio();
        this.fechaFin = viaje.getFechaFin();
        this.horaFin = viaje.getHoraFin();
        this.idUsuario = viaje.getIdUsuario();
        this.idMonopatin = viaje.getIdMonopatin();
        this.pausa = viaje.getPausa();
    }
}
