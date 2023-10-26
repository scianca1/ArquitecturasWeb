package com.example.microadmin.servicios;

import com.example.microadmin.repositorios.ReporteMonopatinRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class ReporteMonopatinServicio implements BaseServicio{

    @Autowired
    private ReporteMonopatinRepositorio repositorio;

    @Override
    public List findAll() throws Exception {
        return null;
    }

    @Override
    public Object findById(Long id) throws Exception {
        return repositorio.findById(id);
    }

    public List<Serializable> getReportePorKm() {
        return repositorio.getReportePorKm();
    }

    public List<Serializable> getReportePortiempoConPausa() {
        return repositorio.getReportePorTiempoConPausa();
    }

    public List<Serializable> getReportePorTiempoSinPausa() {
        return repositorio.getReportePorTiempoSinPausa();
    }

    public List<Serializable> getReportePorKmYTiempoConPausa() {
        return repositorio.getReportePorKmYTiempoConPausa();
    }

    public List<Serializable> getReportePorViajePorAnio(int anio) {
        return repositorio.getReportePorViajePorAnio(anio);
    }

    @Override
    public Object save(Object entity) throws Exception {
        return null;
    }
}
