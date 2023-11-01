package com.example.microadmin.dtos.reporteDto;

import com.example.microadmin.dtos.MonopatinIdDto;
import com.example.microadmin.dtos.ViajeDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportePorCantViajes implements Serializable {
    public Long id;
    public Long viajesTotales;

    public List<ReportePorCantViajes> generarReporte(List<ViajeDto> lista, Integer cant) {
        List<ReportePorCantViajes> reporte = new ArrayList<>();
        Map<Long, Integer> contadorViajes = new HashMap<>();
        for (ViajeDto viaje : lista) {
            Long idMonopatin = viaje.getIdMonopatin();
            contadorViajes.put(idMonopatin, contadorViajes.getOrDefault(idMonopatin, 0) + 1);
        }
        for (Map.Entry<Long, Integer> monopatin : contadorViajes.entrySet()) {
            if (monopatin.getValue() >= cant) {
                ReportePorCantViajes r = new ReportePorCantViajes();
                r.id = monopatin.getKey();
                r.viajesTotales = Long.valueOf(monopatin.getValue());
                reporte.add(r);
            }
        }
        return reporte;
    }


}
