package com.example.micromonopatines.utils;

import com.example.micromonopatines.dtos.MonopatinDto;
import com.example.micromonopatines.dtos.ParadaDto;
import com.example.micromonopatines.entitys.Monopatin;
import com.example.micromonopatines.entitys.Parada;
import com.example.micromonopatines.repositorios.MonopatinRepositorio;
import com.example.micromonopatines.repositorios.ParadaRepositorio;
import com.example.micromonopatines.servicios.MonopatinServicio;
import com.example.micromonopatines.servicios.ParadaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataUpload {

    private final MonopatinServicio monopatinService;
    private final ParadaServicio paradaService;

    @Autowired
    public DataUpload(MonopatinServicio monopatinService, ParadaServicio paradaService) {
        this.monopatinService = monopatinService;
        this.paradaService = paradaService;
    }

    public void loadData() throws Exception {
        MonopatinDto m1 = new MonopatinDto(1L, 1L, true, 0.0, 0.0, 0.0);
        MonopatinDto m2 = new MonopatinDto(10L, 15L, false, 50.0, 20.5, 25.0);
        ParadaDto p1 = new ParadaDto(0L, 0L);
        ParadaDto p2 = new ParadaDto(10L, 15L);
        this.monopatinService.save(m1);
        this.monopatinService.save(m2);
        this.paradaService.save(p1);
        this.paradaService.save(p2);
    }

}