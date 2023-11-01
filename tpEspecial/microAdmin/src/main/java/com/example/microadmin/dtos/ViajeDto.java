package com.example.microadmin.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    private int pausa;

}
