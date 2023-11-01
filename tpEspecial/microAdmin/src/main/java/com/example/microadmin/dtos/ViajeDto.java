package com.example.microadmin.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
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
    private boolean viajePausado;
    private int valorViaje;
    private int pausa;
    private LocalTime horaInicioPausa;
    private LocalTime horaFinPausa;

    public ViajeDto(){}

    public ViajeDto(LocalDate fechaInicio, LocalTime horaInicio, LocalDate fechaFin, LocalTime horaFin, Long idUsuario, Long idMonopatin, int pausa, Long idCuenta, Long idParadaOrigen, Long idParadaDestino, boolean pausado, int valor, LocalTime horaInicioPausa, LocalTime horaFinPausa) {
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
    public ViajeDto( Long idUsuario, Long idMonopatin, Long idCuenta, Long idParadaOrigen, Long idParadaDestino) {
        this.idUsuario = idUsuario;
        this.idMonopatin = idMonopatin;
        this.idCuenta=idCuenta;
        this.idParadaOrigen=idParadaOrigen;
        this.idParadaDestino=idParadaDestino;
    }

}
