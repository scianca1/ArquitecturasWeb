package com.example.microadmin.dtos.reporteDto;

import com.example.microadmin.dtos.MonopatinIdDto;

import java.io.Serializable;
import java.util.List;

public class ReportePorCantViajes implements Serializable {
    public Long id;
    public double kmRecorridos;
    public Long viajesTotales;

    public List<ReportePorCantViajes> generarReporte(List<MonopatinIdDto> lista) {
        return null;
    }
}
