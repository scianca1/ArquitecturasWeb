package com.example.micromonopatines.servicios;

import com.example.micromonopatines.dtos.ParadaDto;
import com.example.micromonopatines.entitys.Monopatin;
import com.example.micromonopatines.repositorios.MonopatinRepositorio;
import com.example.micromonopatines.repositorios.ParadaRepositorio;
import com.example.micromonopatines.dtos.ParadaDtoConId;
import com.example.micromonopatines.entitys.Parada;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
public class ParadaServicio implements BaseServicio<ParadaDto> {
    ParadaRepositorio repositorio;
    MonopatinRepositorio monopatinRepositorio;

    public ParadaServicio(ParadaRepositorio pr, MonopatinRepositorio mr)
    {
        this.repositorio = pr;
        this.monopatinRepositorio = mr;
    }
    @Override
    public List findAll() throws Exception {
        return this.repositorio.findAll().stream().map(ParadaDtoConId::new).toList();
    }

    @Override
    public ParadaDto findById(String id) throws Exception {
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

    public ParadaDtoConId addMonopatin (String idMono, String idPara)throws IOException {

        Optional<Monopatin> opM=this.monopatinRepositorio.findById(idMono);
        Optional<Parada> opP=repositorio.findById(idPara);
        System.out.println(opP);
        System.out.println(opM);
        if(opM.isPresent()&&opP.isPresent()){
            Monopatin m=opM.get();
            Parada p = opP.get();
            p.addMonopatin(m);
            ParadaDtoConId pDto= new ParadaDtoConId(p);
            repositorio.save(p);
            return pDto;
        }
        return null;
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
    public ParadaDto delete(String id) throws Exception {
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
