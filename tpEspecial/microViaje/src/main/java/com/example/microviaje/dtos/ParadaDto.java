package com.example.microviaje.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@AllArgsConstructor
public class ParadaDto {
    private Long x;
    private Long y;
    private  List<String> monopatines;

    public ParadaDto(){
        this.monopatines = new ArrayList<>();
    }

}
