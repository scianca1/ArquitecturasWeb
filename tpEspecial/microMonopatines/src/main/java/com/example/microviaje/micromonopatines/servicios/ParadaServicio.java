package com.example.microviaje.micromonopatines.servicios;

import com.example.microviaje.micromonopatines.dtos.ParadaDto;
import com.example.microviaje.micromonopatines.dtos.ParadaDtoConId;
import com.example.microviaje.micromonopatines.entitys.Parada;
import com.example.microviaje.micromonopatines.repositorios.ParadaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ParadaServicio implements BaseServicio<ParadaDto> {
    ParadaRepositorio repositorio;

    public ParadaServicio(ParadaRepositorio pr){
        this.repositorio = pr;
    }
    @Override
    public List findAll() throws Exception {
        return this.repositorio.findAll().stream().map(ParadaDtoConId::new).toList();
    }

    @Override
    public ParadaDto findById(Long id) throws Exception {
        ParadaDtoConId p = this.repositorio.findById(id).map(ParadaDtoConId::new).orElseThrow(
                ()->new IllegalArgumentException("ID invalido:"+id)
        );
        return p;
    }

    @Override
    public ParadaDto save(ParadaDto entity) throws Exception {
        Parada parada = new Parada(entity);
        parada = this.repositorio.save(parada);
        return new ParadaDtoConId(parada);
    }

    public ParadaDtoConId put(ParadaDtoConId paradaDtoConId) {
        Optional<Parada> opP = this.repositorio.findById(paradaDtoConId.getId());
        if(opP.isPresent()){
            Parada parada = opP.get();
            parada.setX(paradaDtoConId.getX());
            parada.setY(paradaDtoConId.getY());
            parada.setId(paradaDtoConId.getId());
            repositorio.save(parada);
            return paradaDtoConId;
        }
        return null;
    }

    @Override
    public ParadaDto delete(Long id) throws Exception {
        Optional<Parada> p = repositorio.findById(id);

        if (p.isPresent()) {
            Parada parada = p.get();
            this.repositorio.delete(parada);
            ParadaDtoConId pDto = new ParadaDtoConId(parada);
            return  pDto;
        }
        return null;
    }
}
