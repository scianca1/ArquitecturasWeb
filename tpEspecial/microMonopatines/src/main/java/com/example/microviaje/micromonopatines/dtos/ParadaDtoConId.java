package com.example.microviaje.micromonopatines.dtos;


import com.example.microviaje.micromonopatines.entitys.Parada;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParadaDtoConId extends ParadaDto{
    Long id;
    public ParadaDtoConId(Long id, Long x, Long y){
        super(x, y);
        this.id = id;
    }
    public ParadaDtoConId(Parada p){
        super(p.getX(), p.getY());
        this.id = p.getId();
    }
}
