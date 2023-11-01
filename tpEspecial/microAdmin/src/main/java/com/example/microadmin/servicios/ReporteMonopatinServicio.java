package com.example.microadmin.servicios;

import com.example.microadmin.dtos.MonopatinIdDto;
import com.example.microadmin.dtos.reporteDto.ReporteDeUsoPorKm;
import com.example.microadmin.dtos.reporteDto.ReportePorCantViajes;
import com.example.microadmin.dtos.reporteDto.ReportePorTiempoConPausas;
import com.example.microadmin.dtos.reporteDto.ReportePorTiempoSinPausas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.Serializable;
import java.util.List;

@Service
public class ReporteMonopatinServicio implements BaseServicio{

    @Autowired
    private RestTemplate monopatinClienteRest;

    @Override
    public List findAll() throws Exception {
        return null;
    }

    @Override
    public Object findById(Long id) throws Exception {
        return null;
    }

    public List<MonopatinIdDto> getMonopatines() {
        HttpHeaders cabecera = new HttpHeaders();
        HttpEntity<MonopatinIdDto> objetoMonopatin = new HttpEntity<>(cabecera);
        ResponseEntity<List<MonopatinIdDto>> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8001/monopatines",
                HttpMethod.GET,
                objetoMonopatin,
                new ParameterizedTypeReference<>() {
                }
        );
        cabecera.setContentType(MediaType.APPLICATION_JSON);
        List<MonopatinIdDto> lista = respuesta.getBody();
        return lista;
    }

    public List<ReporteDeUsoPorKm> getReportePorKm() {
        ReporteDeUsoPorKm r= new ReporteDeUsoPorKm();
        List<MonopatinIdDto> lista= this.getMonopatines();
        List<ReporteDeUsoPorKm> reporte= r.generarReporte(lista);
        return reporte;
    }

    public List<ReportePorTiempoConPausas> getReportePortiempoConPausa() {
        ReportePorTiempoConPausas r = new ReportePorTiempoConPausas();
        List<MonopatinIdDto> lista = this.getMonopatines();
        List<ReportePorTiempoConPausas> reporte = r.generarReporte(lista);
        return reporte;
    }

    public List<ReportePorTiempoSinPausas> getReportePorTiempoSinPausa() {
        ReportePorTiempoSinPausas r= new ReportePorTiempoSinPausas();
        List<MonopatinIdDto> lista= this.getMonopatines();
        List<ReportePorTiempoSinPausas> reporte= r.generarReporte(lista);
        return reporte;
    }

    public List<ReportePorCantViajes> getReportePorViajePorAnio(int anio) {
        ReportePorCantViajes r= new ReportePorCantViajes();
        List<MonopatinIdDto> lista= this.getMonopatines();
        List<ReportePorCantViajes> reporte= r.generarReporte(lista);
        return reporte;
    }

    public List<Serializable> getReportePorKmYTiempoConPausa() {
        return null;
    }

    @Override
    public Object save(Object entity) throws Exception {
        return null;
    }
}
