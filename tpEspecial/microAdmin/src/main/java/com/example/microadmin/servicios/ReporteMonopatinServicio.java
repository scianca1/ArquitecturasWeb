package com.example.microadmin.servicios;

import com.example.microadmin.dtos.MonopatinDto;
import com.example.microadmin.dtos.MonopatinIdDto;
import com.example.microadmin.dtos.ViajeDto;
import com.example.microadmin.dtos.reporteDto.*;
import com.example.microadmin.repositorios.ReporteMonopatinRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.io.Serializable;
import java.util.List;

@Service
public class ReporteMonopatinServicio implements BaseServicio{

    @Autowired
    private RestTemplate monopatinClienteRest;
    @Autowired
    private ReporteMonopatinRepositorio repositorio;

    @Override
    public List findAll() throws Exception {
        return null;
    }

    @Override
    public Object findById(Long id) throws Exception {
        return null;
    }

    @Transactional(readOnly = true)
    public List<MonopatinIdDto> getMonopatines() {
        HttpHeaders cabecera = new HttpHeaders();
        HttpEntity<MonopatinIdDto> objetoMonopatin = new HttpEntity<>(cabecera);
        ResponseEntity<List<MonopatinIdDto>> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8001/monopatin",
                HttpMethod.GET,
                objetoMonopatin,
                new ParameterizedTypeReference<>(){}
        );
        cabecera.setContentType(MediaType.APPLICATION_JSON);
        List<MonopatinIdDto> lista = respuesta.getBody();
        return lista;
    }

    @Transactional(readOnly = true)
    public List<ReporteDeUsoPorKm> getReportePorKm() {
        ReporteDeUsoPorKm r= new ReporteDeUsoPorKm();
        List<MonopatinIdDto> lista= this.getMonopatines();
        List<ReporteDeUsoPorKm> reporte= r.generarReporte(lista);
        return reporte;
    }

    @Transactional(readOnly = true)
    public List<ReportePorTiempoConPausas> getReportePortiempoConPausa() {
        ReportePorTiempoConPausas r = new ReportePorTiempoConPausas();
        List<MonopatinIdDto> lista = this.getMonopatines();
        List<ReportePorTiempoConPausas> reporte = r.generarReporte(lista);
        return reporte;
    }

    @Transactional(readOnly = true)
    public List<ReportePorTiempoSinPausas> getReportePorTiempoSinPausa() {
        ReportePorTiempoSinPausas r= new ReportePorTiempoSinPausas();
        List<MonopatinIdDto> lista= this.getMonopatines();
        List<ReportePorTiempoSinPausas> reporte= r.generarReporte(lista);
        return reporte;
    }

    @Transactional(readOnly = true)
    public ReporteOperablesVsMantenimiento getReporteOperablesVsMantenimiento() {
        ReporteOperablesVsMantenimiento r= new ReporteOperablesVsMantenimiento();
        List<MonopatinIdDto> lista= this.getMonopatines();
        ReporteOperablesVsMantenimiento reporte= r.generarReporte(lista);
        return reporte;
    }

    @Transactional(readOnly = true)
    public List<ReportePorCantViajes> getCantViajesMonopatinPorAnio(int cant, Integer anio) {
        HttpHeaders cabecera = new HttpHeaders();
        HttpEntity<ViajeDto> solicitud = new HttpEntity<>(cabecera);
        ResponseEntity<List<ViajeDto>> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8004/viaje/anio/" + anio,
                HttpMethod.GET,
                solicitud,
                new ParameterizedTypeReference<>() {}
        );
        cabecera.setContentType(MediaType.APPLICATION_JSON);
        List<ViajeDto> lista = respuesta.getBody();
        ReportePorCantViajes r= new ReportePorCantViajes();
        List<ReportePorCantViajes> reporte = r.generarReporte(lista, cant);
        return reporte;
    }

    @Override
    public Object save(Object entity) throws Exception {
        return null;
    }


}
