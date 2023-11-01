package com.example.micromonopatines.dtos;

import com.example.micromonopatines.entitys.Monopatin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonopatinDtoConId extends MonopatinDto {
    private Long id;

    public MonopatinDtoConId(Monopatin monopatin) {
        super(monopatin);
        this.id = monopatin.getId();
    }
    public MonopatinDtoConId(Long id, Long x, Long y, boolean habilitado, double kmRecorridos, double tiempoDeUso, double tiempoDeUsoConPausa) {
        super(x, y, habilitado, kmRecorridos, tiempoDeUso, tiempoDeUsoConPausa);
        this.id = id;
    }
}
