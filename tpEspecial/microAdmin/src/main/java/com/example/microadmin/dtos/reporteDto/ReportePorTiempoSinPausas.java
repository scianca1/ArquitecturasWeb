package com.example.microadmin.dtos.reporteDto;

import com.example.microadmin.dtos.MonopatinIdDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportePorTiempoSinPausas implements Serializable {
    private String id;
    private double kmRecorridos;
    private double tiempoDeUso;

    public List<ReportePorTiempoSinPausas> generarReporte(List<MonopatinIdDto> monopatines) {
        List<ReportePorTiempoSinPausas> reporte = new ArrayList<>();

        for (MonopatinIdDto monopatin : monopatines) {
            ReportePorTiempoSinPausas r = new ReportePorTiempoSinPausas();
            r.setId(monopatin.getId());
            r.setKmRecorridos(monopatin.getKmRecorridos());
            r.setTiempoDeUso(monopatin.getTiempoDeUso());
            reporte.add(r);
        }
        Collections.sort(reporte, Comparator.comparing(ReportePorTiempoSinPausas::getTiempoDeUso).reversed());
        return reporte;
    }
}


