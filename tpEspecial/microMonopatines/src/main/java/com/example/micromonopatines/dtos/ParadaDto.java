package com.example.micromonopatines.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class ParadaDto {
    private Long id;
    private Long x;
    private Long y;
    List<MonopatinDtoConId> monopatines;
}
