package com.example.microusuarios.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonopatinDto {
    protected Long id;
    protected Long x;
    protected Long y;
    protected boolean habilitado;
    protected double kmRecorridos;
    protected double tiempoDeUso;
    protected double tiempoDeUsoConPausa;


    public MonopatinDto(){}
    public MonopatinDto(Long x, Long y, boolean habilitado, double kmRecorridos, double tiempoDeUso, double tiempoDeUsoConPausa) {
        this.x = x;
        this.y = y;
        this.habilitado = habilitado;
        this.kmRecorridos = kmRecorridos;
        this.tiempoDeUso = tiempoDeUso;
        this.tiempoDeUsoConPausa = tiempoDeUsoConPausa;
    }
}
