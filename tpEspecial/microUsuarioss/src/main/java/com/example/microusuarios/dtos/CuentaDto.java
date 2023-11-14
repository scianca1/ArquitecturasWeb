package com.example.microusuarios.dtos;

import com.example.microusuarios.entitys.Cuenta;
import com.example.microusuarios.entitys.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CuentaDto {

    private Long id;

    private List<UsuarioDto> usuarios;

    private boolean anulada;

    private String cuentaMercadoPago;

    private Date fechaDeAlta;

    private Integer saldo;

    public CuentaDto() {
    }

    public CuentaDto(Long id, List<Usuario> usuarios, boolean anulada, String cuentaMercadoPago, Date fechaDeAlta, Integer saldo) {
        this.id = id;
        this.usuarios= new ArrayList<UsuarioDto>();
        for (Usuario u:usuarios) {
            this.usuarios.add(new UsuarioDto(u));
        }
        this.anulada = anulada;
        this.cuentaMercadoPago = cuentaMercadoPago;
        this.fechaDeAlta = fechaDeAlta;
        this.saldo = saldo;
    }
    public CuentaDto( List<Usuario> usuarios, boolean anulada, String cuentaMercadoPago, Date fechaDeAlta, Integer saldo) {
        this.id=null;
        this.usuarios= new ArrayList<UsuarioDto>();
        for (Usuario u:usuarios) {
            this.usuarios.add(new UsuarioDto(u));
        }
        this.anulada = anulada;
        this.cuentaMercadoPago = cuentaMercadoPago;
        this.fechaDeAlta = fechaDeAlta;
        this.saldo = saldo;
    }

    public CuentaDto(Cuenta cuenta) {
        this.id = cuenta.getId();
        this.anulada = cuenta.isAnulada();
        this.cuentaMercadoPago = cuenta.getCuentaMercadoPago();
        this.fechaDeAlta = cuenta.getFechaDeAlta();
        this.saldo = cuenta.getSaldo();
        this.usuarios= new ArrayList<UsuarioDto>();
    }
}
