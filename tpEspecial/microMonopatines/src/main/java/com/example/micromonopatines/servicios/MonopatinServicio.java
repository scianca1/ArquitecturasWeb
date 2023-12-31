package com.example.micromonopatines.servicios;

import com.example.micromonopatines.dtos.MonopatinDto;
import com.example.micromonopatines.dtos.MonopatinDtoConId;
import com.example.micromonopatines.entitys.Monopatin;
import com.example.micromonopatines.repositorios.MonopatinRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonopatinServicio implements BaseServicio<MonopatinDto>{
    MonopatinRepositorio repositorio;
    public MonopatinServicio(MonopatinRepositorio mr) {
        this.repositorio = mr;
    }


    @Override
    public List findAll() throws Exception {
        System.out.println(1);
        //si parametrizo la lista deberia retornar sin id :/
        return this.repositorio.findAll().stream().map(MonopatinDtoConId::new).toList();
    }

    @Override
    public MonopatinDtoConId findById(String id) throws Exception {
        MonopatinDtoConId m = this.repositorio.findById(id).map(MonopatinDtoConId::new).orElseThrow(
                ()->new IllegalArgumentException("ID invalido:"+id)
        );
        System.out.println(m.getId());
        return m;
    }

    @Override
    public MonopatinDto save(MonopatinDto entity) throws Exception {
        Monopatin monopatin = new Monopatin(entity);
        monopatin = this.repositorio.save(monopatin);
        return new MonopatinDtoConId(monopatin);
    }

    public MonopatinDtoConId put(MonopatinDtoConId monopatinDtoConId) {
        Optional<Monopatin> opM = repositorio.findById(monopatinDtoConId.getId());
        if(opM.isPresent()){
            Monopatin monopatin = opM.get();
            monopatin.setX(monopatinDtoConId.getX());
            monopatin.setY(monopatinDtoConId.getY());
            monopatin.setId(monopatinDtoConId.getId());
            monopatin.setHabilitado(monopatinDtoConId.isHabilitado());
            monopatin.setKmRecorridos(monopatinDtoConId.getKmRecorridos());
            monopatin.setTiempoDeUso(monopatinDtoConId.getTiempoDeUso());
            monopatin.setTiempoDeUsoConPausa(monopatinDtoConId.getTiempoDeUsoConPausa());
            repositorio.save(monopatin);
            return monopatinDtoConId;
        }
        return null;
    }

    public MonopatinDtoConId habilitar(String id, boolean isHabilitado) throws Exception {
            MonopatinDtoConId monopatin = this.findById(id);
            if(monopatin != null){
                Monopatin mono = new Monopatin(monopatin) ;
                mono.setHabilitado(isHabilitado);
                this.repositorio.save(mono);
                monopatin.setHabilitado(isHabilitado);
                return monopatin;
            }
            else {
                throw new Exception();
            }
    }
    public MonopatinDtoConId addKmRecorridos(String id, double kms) throws Exception {
            MonopatinDtoConId monopatin = this.findById(id);
            if(monopatin != null){
                Monopatin mono = new Monopatin(monopatin) ;
                mono.setKmRecorridos(mono.getKmRecorridos() + kms);
                this.repositorio.save(mono);
                return monopatin;
            }
            else {
                throw new Exception();
            }
    }

    @Override
    public MonopatinDto delete(String id) throws Exception {
        Optional<Monopatin> m = repositorio.findById(id);

        if (m.isPresent()) {
            Monopatin monopatin = m.get();
            MonopatinDto mDto = new MonopatinDto(monopatin);
            this.repositorio.delete(monopatin);
            return  mDto;
        }
        return null;
    }
}
