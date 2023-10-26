package com.example.microadmin.dtos.reporteDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class ReportePorTiempoSinPausas implements Serializable {
    private Long id;
    private double kmRecorridos;
}


