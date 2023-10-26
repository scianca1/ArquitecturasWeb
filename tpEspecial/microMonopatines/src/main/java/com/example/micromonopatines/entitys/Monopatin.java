package com.example.micromonopatines.entitys;
import java.io.Serializable;

import com.example.micromonopatines.dtos.MonopatinDtoConId;
import com.example.micromonopatines.dtos.MonopatinDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "Monopatin")
@Getter
@Setter
public class Monopatin implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column
    private Long x;
    @Column
    private Long y;
    @Column
    private boolean habilitado;
    @Column
    private long kmRecorridos;
    @Column
    private double tiempoDeUso;
    @Column
    private double tiempoDeUsoConPausa;

    public Monopatin() {
    }

    public Monopatin(Long x, Long y, boolean habilitado, long kmRecorridos, double tiempoDeUso, double tiempoDeUsoConPausa) {
        this.x = x;
        this.y = y;
        this.habilitado = habilitado;
        this.kmRecorridos = kmRecorridos;
        this.tiempoDeUso = tiempoDeUso;
        this.tiempoDeUsoConPausa = tiempoDeUsoConPausa;
    }

    public Monopatin(MonopatinDto entity) {
        this.x = entity.getX();
        this.y = entity.getY();
        this.habilitado = entity.isHabilitado();
        this.kmRecorridos = entity.getKmRecorridos();
        this.tiempoDeUso = entity.getTiempoDeUso();
        this.tiempoDeUsoConPausa = entity.getTiempoDeUsoConPausa();
    }
    public Monopatin(MonopatinDtoConId entity) {
        this.id = entity.getId();
        this.x = entity.getX();
        this.y = entity.getY();
        this.habilitado = entity.isHabilitado();
        this.kmRecorridos = entity.getKmRecorridos();
        this.tiempoDeUso = entity.getTiempoDeUso();
        this.tiempoDeUsoConPausa = entity.getTiempoDeUsoConPausa();
    }


    public Long getId() {
        return id;
    }
    public boolean getHablilitado() {
        return this.habilitado;
    }
}
