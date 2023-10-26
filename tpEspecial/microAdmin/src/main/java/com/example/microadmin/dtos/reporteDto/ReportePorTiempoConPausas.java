package com.example.microadmin.dtos.reporteDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class ReportePorTiempoConPausas implements Serializable {
    private Long id;
    private double kmRecorridos;
    private double tiempoPausa;
}

