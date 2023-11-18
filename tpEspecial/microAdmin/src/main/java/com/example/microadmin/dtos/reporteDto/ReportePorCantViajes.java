package com.example.microadmin.dtos.reporteDto;

import com.example.microadmin.dtos.ViajeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportePorCantViajes implements Serializable {
    public String idMonopatin;
    public Long viajesTotales;

    public List<ReportePorCantViajes> generarReporte(List<ViajeDto> lista, Integer cant) {
        List<ReportePorCantViajes> reporte = new ArrayList<>();
        Map<String, Integer> contadorViajes = new HashMap<>();
        for (ViajeDto viaje : lista) {
            String idMonopatin = viaje.getIdMonopatin();
            contadorViajes.put(idMonopatin, contadorViajes.getOrDefault(idMonopatin, 0) + 1);
        }
        for (Map.Entry<String, Integer> monopatin : contadorViajes.entrySet()) {
            if (monopatin.getValue() >= cant) {
                ReportePorCantViajes r = new ReportePorCantViajes();
                r.idMonopatin = monopatin.getKey();
                r.viajesTotales = Long.valueOf(monopatin.getValue());
                reporte.add(r);
            }
        }
        return reporte;
    }


}
