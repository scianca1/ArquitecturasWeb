package com.example.microusuarios.entitys;

import com.example.microusuarios.dtos.CuentaDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Cuenta")
@Getter
@Setter
public class Cuenta{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToMany(mappedBy = "cuentas")
    private List<Usuario> usuarios;

    @Column
    private boolean anulada;

    @Column
    private String cuentaMercadoPago;

    @Column
    private Date fechaDeAlta;

    @Column
    private Integer saldo;

    public Cuenta() {
        this.usuarios= new ArrayList<>();
    }

    public Cuenta( boolean anulada, String cuentaMercadoPago, Date fechaDeAlta, Integer saldo) {
        this.usuarios = new ArrayList<Usuario>();
        this.anulada = anulada;
        this.cuentaMercadoPago = cuentaMercadoPago;
        this.fechaDeAlta = fechaDeAlta;
        this.saldo = saldo;
    }
    public Cuenta( long id,boolean anulada, String cuentaMercadoPago, Date fechaDeAlta, Integer saldo) {
        this.id=id;
        this.usuarios = new ArrayList<Usuario>();
        this.anulada = anulada;
        this.cuentaMercadoPago = cuentaMercadoPago;
        this.fechaDeAlta = fechaDeAlta;
        this.saldo = saldo;
    }
    public Cuenta(CuentaDto c) {
        this.id=c.getId();
        this.anulada = c.isAnulada();
        this.cuentaMercadoPago = c.getCuentaMercadoPago();
        this.fechaDeAlta = c.getFechaDeAlta();
        this.saldo = c.getSaldo();
        this.usuarios = c.getUsuarios().stream().map(Usuario::new).toList();
    }


    public Long getId() {
        return id;
    }

    public void addUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }
}
