package com.example.microadmin.dtos.reporteDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;
@Data
@AllArgsConstructor
public class ReporteDeUsoPorKm implements Serializable {
    public Long id;
    public double kmRecorridos;
}

