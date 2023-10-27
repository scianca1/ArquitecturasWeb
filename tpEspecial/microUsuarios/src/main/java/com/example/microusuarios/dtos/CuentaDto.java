package com.example.microusuarios.dtos;

import com.example.microusuarios.entitys.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CuentaDto {

    private Long id;

    private List<Usuario> usuarios;

    private boolean anulada;

    private String cuentaMercadoPago;

    private Date fechaDeAlta;

    private Integer saldo;

    public CuentaDto() {
    }

    public CuentaDto(Long id, List<Usuario> usuarios, boolean anulada, String cuentaMercadoPago, Date fechaDeAlta, Integer saldo) {
        this.id = id;
        this.usuarios = usuarios;
        this.anulada = anulada;
        this.cuentaMercadoPago = cuentaMercadoPago;
        this.fechaDeAlta = fechaDeAlta;
        this.saldo = saldo;
    }
}
