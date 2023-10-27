package com.example.micromonopatines.servicios;

import com.example.micromonopatines.dtos.MonopatinDtoConId;
import com.example.micromonopatines.dtos.MonopatinDto;
import com.example.micromonopatines.entitys.Monopatin;
import com.example.micromonopatines.dtos.UsuarioDto;
import com.example.micromonopatines.repositorios.MonopatinRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MonopatinServicio implements BaseServicio<MonopatinDto>{
    MonopatinRepositorio repositorio;
    private RestTemplate rest;
    public MonopatinServicio(MonopatinRepositorio mr) {
        this.repositorio = mr;
        rest= new RestTemplate();
    }
    public void  probando(Long usuarioId){
        ResponseEntity<UsuarioDto> respuesta=this.rest.getForEntity("http://localhost:8080/usuario/id/"+usuarioId, UsuarioDto.class);
        UsuarioDto usuario=respuesta.getBody();
        System.out.println(usuario.getNombre());
    }


    @Override
    public List findAll() throws Exception {
        //si parametrizo la lista deberia retornar sin id :/
        return this.repositorio.findAll().stream().map(MonopatinDtoConId::new).toList();
    }

    @Override
    public MonopatinDtoConId findById(Long id) throws Exception {
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
        Monopatin monopatin = new Monopatin(monopatinDtoConId);
        this.repositorio.put(monopatin,monopatin.getId());
        return monopatinDtoConId;
    }

    @Override
    public MonopatinDto delete(Long id) throws Exception {
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
