package com.example.microadmin.dtos;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ViajeDto {
    private LocalDate fechaInicio;
    private Long idMonopatin;
    private int valorViaje;

}
