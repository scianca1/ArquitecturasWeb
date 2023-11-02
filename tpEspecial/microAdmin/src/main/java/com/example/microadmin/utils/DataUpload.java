package com.example.microadmin.utils;

import com.example.microadmin.entitys.Administrador;
import com.example.microadmin.repositorios.AdminRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataUpload {

    private final AdminRepositorio repositorio;

    @Autowired
    public DataUpload(AdminRepositorio repositorio) {
        this.repositorio = repositorio;

    }

    public void loadData() throws IOException {
        Administrador administrador = new Administrador();
        administrador.setId(1L);
        administrador.setTarifa(500);
        administrador.setTarifaPorPausaExtensa(900);
        repositorio.save(administrador);

    }

}

