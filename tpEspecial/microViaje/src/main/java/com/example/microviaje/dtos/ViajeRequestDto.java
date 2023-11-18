package com.example.microviaje.dtos;

import com.example.microviaje.entitys.Viaje;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ViajeRequestDto {
//    Long idUsuario, String idMonopatin, Long idCuenta, String origen, String destino
        private Long idUsuario;
        private String idMonopatin;
        private Long idCuenta;
        private String idParadaOrigen;
        private String idParadaDestino;
        public ViajeRequestDto(){}

        public ViajeRequestDto(Long idUsuario, String idMonopatin, Long idCuenta, String origen, String destino) {
            this.idUsuario = idUsuario;
            this.idMonopatin = idMonopatin;
            this.idCuenta= idCuenta;
            this.idParadaOrigen= origen;
            this.idParadaDestino= destino;
        }
    }
