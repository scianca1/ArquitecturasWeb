package com.example.microviaje.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParadaDto {
    private Long x;
    private Long y;
    private  List<MonopatinDto> monopatines;

}
