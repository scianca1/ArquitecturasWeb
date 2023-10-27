package com.example.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.Getter;

import java.util.Date;
import java.util.List;
@Getter
public class CuentaDto {
    private boolean anulada;
    private String cuentaMercadoPago;
    private Date fechaDeAlta;
    private Integer saldo;

    public CuentaDto(boolean anulada, String cuentaMercadoPago, Date fechaDeAlta, Integer saldo) {
        this.anulada = anulada;
        this.cuentaMercadoPago = cuentaMercadoPago;
        this.fechaDeAlta = fechaDeAlta;
        this.saldo = saldo;
    }
}
