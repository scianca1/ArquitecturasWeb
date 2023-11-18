package com.example.microadmin.servicios;

import com.example.microadmin.dtos.MonopatinDto;
import com.example.microadmin.dtos.MonopatinIdDto;
import com.example.microadmin.dtos.ViajeDto;
import com.example.microadmin.dtos.reporteDto.*;
import com.example.microadmin.repositorios.ReporteMonopatinRepositorio;
import jakarta.servlet.http.HttpServletRequest;
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
    public List<MonopatinIdDto> getMonopatines(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.set("Authorization", token);
        cabecera.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MonopatinIdDto> objetoMonopatin = new HttpEntity<>(cabecera);
        ResponseEntity<List<MonopatinIdDto>> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8001/monopatin",
                HttpMethod.GET,
                objetoMonopatin,
                new ParameterizedTypeReference<>(){}
        );
        List<MonopatinIdDto> lista = respuesta.getBody();
        return lista;
    }

    @Transactional(readOnly = true)
    public List<ReporteDeUsoPorKm> getReportePorKm(HttpServletRequest request) {
        ReporteDeUsoPorKm r= new ReporteDeUsoPorKm();
        List<MonopatinIdDto> lista= this.getMonopatines(request);
        List<ReporteDeUsoPorKm> reporte= r.generarReporte(lista);
        return reporte;
    }

    @Transactional(readOnly = true)
    public List<ReportePorTiempoConPausas> getReportePortiempoConPausa(HttpServletRequest request) {
        ReportePorTiempoConPausas r = new ReportePorTiempoConPausas();
        List<MonopatinIdDto> lista = this.getMonopatines(request);
        List<ReportePorTiempoConPausas> reporte = r.generarReporte(lista);
        return reporte;
    }

    @Transactional(readOnly = true)
    public List<ReportePorTiempoSinPausas> getReportePorTiempoSinPausa(HttpServletRequest request) {
        ReportePorTiempoSinPausas r= new ReportePorTiempoSinPausas();
        List<MonopatinIdDto> lista= this.getMonopatines(request);
        List<ReportePorTiempoSinPausas> reporte= r.generarReporte(lista);
        return reporte;
    }

    @Transactional(readOnly = true)
    public ReporteOperablesVsMantenimiento getReporteOperablesVsMantenimiento(HttpServletRequest request) {
        ReporteOperablesVsMantenimiento r= new ReporteOperablesVsMantenimiento();
        List<MonopatinIdDto> lista= this.getMonopatines(request);
        ReporteOperablesVsMantenimiento reporte= r.generarReporte(lista);
        return reporte;
    }

    @Transactional(readOnly = true)
    public List<ReportePorCantViajes> getCantViajesMonopatinPorAnio(int cant, Integer anio, HttpServletRequest request) {
        System.out.println(1);
        String token = request.getHeader("Authorization");
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.set("Authorization", token);
        cabecera.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ViajeDto> solicitud = new HttpEntity<>(cabecera);
        ResponseEntity<List<ViajeDto>> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8004/viaje/anio/" + anio,
                HttpMethod.GET,
                solicitud,
                new ParameterizedTypeReference<>() {}
        );
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
