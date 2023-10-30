package com.example.micromonopatines.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class ParadaDto{
    private Long x;
    private Long y;
    List<MonopatinDtoConId> monopatines;

    public ParadaDto(Long x, Long y){
        this.x = x;
        this.y = y;
        this.monopatines = new ArrayList<MonopatinDtoConId>();
    }
}
