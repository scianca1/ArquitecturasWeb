package com.example.microviaje.dtos;

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
    private Long id;

    private List<UsuarioDto> usuarios;

    private boolean anulada;

    private String cuentaMercadoPago;

    private Date fechaDeAlta;

    private Integer saldo;

}
