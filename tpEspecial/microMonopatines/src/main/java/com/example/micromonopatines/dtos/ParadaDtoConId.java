package com.example.micromonopatines.dtos;


import com.example.micromonopatines.entitys.Parada;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParadaDtoConId extends ParadaDto{
    String id;
    public ParadaDtoConId(String id, Long x, Long y){
        super(x, y);
        this.id = id;
    }
    public ParadaDtoConId(Parada p){
        super(p.getX(), p.getY());
        this.id = p.getId();
    }
}
