package com.example.microusuarios.servicios;

import com.example.microusuarios.dtos.CuentaDto;
import com.example.microusuarios.dtos.UsuarioDto;
import com.example.microusuarios.repositorios.CuentaRepositorio;
import com.example.microusuarios.repositorios.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CuentaServicio implements BaseServicio<CuentaDto>{
    private CuentaRepositorio repositorio;

    public CuentaServicio(CuentaRepositorio ur){
        this.repositorio= ur;
    }
    @Override
    public List<CuentaDto> findAll() throws Exception {
        return null;
    }

    @Override
    public CuentaDto findById(Long id) throws Exception {
        return null;
    }

    @Override
    public CuentaDto save(CuentaDto c) throws Exception {
        return null;
    }

    @Override
    public CuentaDto delete(Long id) throws Exception {
        return null;
    }
}
