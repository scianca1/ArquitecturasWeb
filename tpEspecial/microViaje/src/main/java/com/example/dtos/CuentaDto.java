package com.example.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDto {
    private boolean anulada;
    private String cuentaMercadoPago;
    private Date fechaDeAlta;
    private Integer saldo;
}
