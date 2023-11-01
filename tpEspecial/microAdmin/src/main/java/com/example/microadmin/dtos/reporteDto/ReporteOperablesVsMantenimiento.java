package com.example.microadmin.dtos.reporteDto;

import com.example.microadmin.dtos.MonopatinIdDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteOperablesVsMantenimiento {
    private int enMantenimiento;
    private int operables;

    public ReporteOperablesVsMantenimiento generarReporte(List<MonopatinIdDto> monopatines) {
        ReporteOperablesVsMantenimiento reporte = new ReporteOperablesVsMantenimiento();
        reporte.enMantenimiento= 0;
        reporte.operables= 0;
        for (MonopatinIdDto monopatin : monopatines) {
            if(monopatin.isHabilitado()){
                reporte.operables++;
            }
            else {
                reporte.enMantenimiento++;
            }
        }
        return reporte;
    }
}
