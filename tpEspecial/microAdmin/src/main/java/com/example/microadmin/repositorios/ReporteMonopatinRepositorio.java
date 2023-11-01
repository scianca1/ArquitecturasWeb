package com.example.microadmin.repositorios;
/*
import com.example.microadmin.entitys.ReporteMonopatin;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface ReporteMonopatinRepositorio extends RepositorioBase <ReporteMonopatin, Integer>{

    @Query(value = "SELECT new com.example.microadmin.dtos.reporteDto.ReporteDeUsoPorKm(rm.id) FROM ReporteMonopatin rm ORDER BY rm.kmRecorridos DESC")
    List<Serializable> getReportePorKm();

    @Query(value = "SELECT new com.example.microadmin.dtos.reporteDto.ReportePorTiempoConPausas(rm.id, rm.tiempoUsadoConPausa) FROM ReporteMonopatin rm ORDER BY rm.tiempoUsadoConPausa DESC")
    List<Serializable> getReportePorTiempoConPausa();

    @Query(value = "SELECT new com.example.microadmin.dtos.reporteDto.ReportePorTiempoSinPausas(rm.id, rm.tiempoUsadoSinPausa) FROM ReporteMonopatin rm ORDER BY rm.tiempoUsadoSinPausa DESC")
    List<Serializable> getReportePorTiempoSinPausa();

    @Query(value = "SELECT new com.example.microadmin.dtos.reporteDto.ReportePorTiempoConPausas(rm.id, rm.kmRecorridos, rm.tiempoUsadoConPausa) FROM ReporteMonopatin rm ORDER BY rm.kmRecorridos DESC ")
    List<Serializable> getReportePorKmYTiempoConPausa();

    @Query(value = "SELECT new com.example.microadmin.dtos.reporteDto.ReportePorCantViajes(rm.id, rm.kmRecorridos, rm.viajesTotales) FROM ReporteMonopatin rm WHERE rm.viajesTotales > ?1 ORDER BY rm.viajesTotales DESC")
    List<Serializable> getReportePorViajePorAnio(int a);
}

 */
