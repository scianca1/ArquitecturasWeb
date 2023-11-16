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
public class ReportePorTiempoConPausas implements Serializable {
    private String id;
    private double kmRecorridos;
    private double tiempoPausa;

    public List<ReportePorTiempoConPausas> generarReporte(List<MonopatinIdDto> monopatines) {
        List<ReportePorTiempoConPausas> reporte = new ArrayList<>();

        for (MonopatinIdDto monopatin : monopatines) {
            ReportePorTiempoConPausas r = new ReportePorTiempoConPausas();
            r.setId(monopatin.getId());
            r.setKmRecorridos(monopatin.getKmRecorridos());
            r.setTiempoPausa(monopatin.getTiempoDeUsoConPausa());
            reporte.add(r);
        }
        Collections.sort(reporte, Comparator.comparing(ReportePorTiempoConPausas::getTiempoPausa).reversed());
        return reporte;
    }
}

