package com.example.microadmin.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ViajeDto {
    private Long idUsuario;
    private String idMonopatin;
    private Long idCuenta;
    private String idParadaOrigen;
    private String idParadaDestino;
    private int valorViaje;
    public ViajeDto(){}

    public ViajeDto(Long idUsuario, String idMonopatin, Long idCuenta, String origen, String destino, int valorViaje) {
        this.idUsuario = idUsuario;
        this.idMonopatin = idMonopatin;
        this.idCuenta= idCuenta;
        this.idParadaOrigen= origen;
        this.idParadaDestino= destino;
    }

}
