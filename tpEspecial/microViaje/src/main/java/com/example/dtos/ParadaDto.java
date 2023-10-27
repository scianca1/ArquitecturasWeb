package com.example.dtos;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class ParadaDto {
    private Long x;
    private Long y;

    public ParadaDto(Long x, Long y) {
        this.x = x;
        this.y = y;
    }
}
