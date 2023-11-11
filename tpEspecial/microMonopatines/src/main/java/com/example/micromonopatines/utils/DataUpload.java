package com.example.micromonopatines.utils;

import com.example.micromonopatines.entitys.Monopatin;
import com.example.micromonopatines.entitys.Parada;
import com.example.micromonopatines.repositorios.MonopatinRepositorio;
import com.example.micromonopatines.repositorios.ParadaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataUpload {

    private final MonopatinRepositorio monopatinRepositorio;
    private final ParadaRepositorio paradaRepositorio;

    @Autowired
    public DataUpload(MonopatinRepositorio monopatinRepositorio, ParadaRepositorio paradaRepositorio) {
        this.monopatinRepositorio = monopatinRepositorio;
        this.paradaRepositorio = paradaRepositorio;
    }

    public void loadData() throws IOException {
//        Monopatin m1 = new Monopatin(1L, 1L, true, 0.0, 0.0, 0.0);
//        Monopatin m2 = new Monopatin(10L, 15L, false, 50.0, 20.5, 25.0);
//        Parada p1 = new Parada(0L, 0L);
//        Parada p2 = new Parada(10L, 15L);
//        this.monopatinRepositorio.save(m1);
//        this.monopatinRepositorio.save(m2);
//        this.paradaRepositorio.save(p1);
//        this.paradaRepositorio.save(p2);
    }

}