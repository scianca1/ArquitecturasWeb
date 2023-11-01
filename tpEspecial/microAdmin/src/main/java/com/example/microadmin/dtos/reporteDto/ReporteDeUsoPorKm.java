package com.example.microadmin.dtos.reporteDto;

import com.example.microadmin.dtos.MonopatinIdDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.io.Serializable;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDeUsoPorKm implements Serializable {
    public Long id;
    public double kmRecorridos;

    public List<ReporteDeUsoPorKm> generarReporte(List<MonopatinIdDto> monopatines) {
        List<ReporteDeUsoPorKm> reportes = new ArrayList<>();

        for (MonopatinIdDto monopatin : monopatines) {
            ReporteDeUsoPorKm r = new ReporteDeUsoPorKm();
            r.setId(monopatin.getId());
            r.setKmRecorridos(monopatin.getKmRecorridos());

            reportes.add(r);
        }
        Collections.sort(reportes, Comparator.comparing(ReporteDeUsoPorKm::getKmRecorridos).reversed());
        return reportes;
    }

}

