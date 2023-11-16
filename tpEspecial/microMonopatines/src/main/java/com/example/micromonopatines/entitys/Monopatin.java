package com.example.micromonopatines.entitys;

import java.io.Serializable;

import com.example.micromonopatines.dtos.MonopatinDto;
import com.example.micromonopatines.dtos.MonopatinDtoConId;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "monopatinn")
@Getter
@Setter
public class Monopatin implements Serializable {
    @Id
    @NotNull
    private String id;
    @NotNull
    private Long x;
    @NotNull
    private Long y;
    @NotNull
    private boolean habilitado;
    @NotNull
    private double kmRecorridos;
    @NotNull
    private double tiempoDeUso;
    @NotNull
    private double tiempoDeUsoConPausa;

    public Monopatin() {
    }

    public Monopatin(Long x, Long y, boolean habilitado, double kmRecorridos, double tiempoDeUso, double tiempoDeUsoConPausa) {
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


    public String getId() {
        return id;
    }
    public boolean getHablilitado() {
        return this.habilitado;
    }
}
