package com.example.micromonopatines.dtos;

import com.example.micromonopatines.entitys.Monopatin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonopatinDto {
    protected Long x;
    protected Long y;
    protected boolean habilitado;
    protected Long kmRecorridos;
    protected double tiempoDeUso;
    protected double tiempoDeUsoConPausa;

    public MonopatinDto(Monopatin monopatin) {
        this.x = monopatin.getX();
        this.y = monopatin.getY();
        this.habilitado = monopatin.getHablilitado();
        this.kmRecorridos = monopatin.getKmRecorridos();
        this.tiempoDeUso = monopatin.getTiempoDeUso();
        this.tiempoDeUsoConPausa = monopatin.getTiempoDeUsoConPausa();
    }

    public MonopatinDto(Long x, Long y, boolean habilitado, Long kmRecorridos, double tiempoDeUso, double tiempoDeUsoConPausa) {
        this.x = x;
        this.y = y;
        this.habilitado = habilitado;
        this.kmRecorridos = kmRecorridos;
        this.tiempoDeUso = tiempoDeUso;
        this.tiempoDeUsoConPausa = tiempoDeUsoConPausa;
    }
}
