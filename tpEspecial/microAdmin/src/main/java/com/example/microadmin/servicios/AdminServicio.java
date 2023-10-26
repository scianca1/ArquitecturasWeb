package com.example.microadmin.servicios;

import com.example.microadmin.dtos.AdminDto;
import com.example.microadmin.dtos.MonopatinDto;
import com.example.microadmin.entitys.Administrador;
import com.example.microadmin.repositorios.AdminRepositorio;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AdminServicio implements BaseServicio{

    private AdminRepositorio repositorio;

    @Autowired
    private RestTemplate monopatinClienteRest;

    public AdminServicio (AdminRepositorio ar){
        this.repositorio= ar;
    }

    public MonopatinDto iniciarMantenimiento (Long idMonopatin){
        //  traigo el monopatin mediante su id
        HttpHeaders cabecera = new HttpHeaders();
        HttpEntity<Void> solicitud1 = new HttpEntity<>(cabecera);
        ResponseEntity<MonopatinDto> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8001/monopatines/" + idMonopatin,
                HttpMethod.GET,
                solicitud1,
                new ParameterizedTypeReference<>() {});

        // Edito el monopatin
        MonopatinDto monopatin = respuesta.getBody();
        monopatin.setHabilitado(false);

        // Lo guardo modificado
        HttpEntity<MonopatinDto> solicitud2 = new HttpEntity<>(monopatin, cabecera);
        ResponseEntity<MonopatinDto> respuesta2 = monopatinClienteRest.exchange(
                "http://localhost:8001/monopatines/" + idMonopatin,
                HttpMethod.PUT,
                solicitud2,
                new ParameterizedTypeReference<>() {});
        cabecera.setContentType(MediaType.APPLICATION_JSON);

        return respuesta2.getBody();
    }

    public MonopatinDto finalizarMantenimiento (Long idMonopatin){
        //  traigo el monopatin mediante su id
        HttpHeaders cabecera = new HttpHeaders();
        HttpEntity<Void> solicitud1 = new HttpEntity<>(cabecera);
        ResponseEntity<MonopatinDto> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8001/monopatines/" + idMonopatin,
                HttpMethod.GET,
                solicitud1,
                new ParameterizedTypeReference<>() {});

        // Edito el monopatin
        MonopatinDto monopatin = respuesta.getBody();
        monopatin.setHabilitado(true);

        // Lo guardo modificado
        HttpEntity<MonopatinDto> solicitud2 = new HttpEntity<>(monopatin, cabecera);
        ResponseEntity<MonopatinDto> respuesta2 = monopatinClienteRest.exchange(
                "http://localhost:8001/monopatines/" + idMonopatin,
                HttpMethod.PUT,
                solicitud2,
                new ParameterizedTypeReference<>() {});
        cabecera.setContentType(MediaType.APPLICATION_JSON);

        return respuesta2.getBody();
    }

    public MonopatinDto addMonopatin(MonopatinDto monopatin) {
        HttpHeaders cabecera = new HttpHeaders();
        HttpEntity<MonopatinDto> objetoMonopatin = new HttpEntity<>(monopatin, cabecera);
        ResponseEntity<MonopatinDto> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8001/monopatines",
                HttpMethod.POST,
                objetoMonopatin,
                new ParameterizedTypeReference<>() {}
        );
        cabecera.setContentType(MediaType.APPLICATION_JSON); //El cuerpo de la solicitud se envia en formato JSON

        return respuesta.getBody();
    }

        public MonopatinDto eliminarMonopatin(Long idMonopatin) throws Exception {
        HttpHeaders cabecera = new HttpHeaders();
        HttpEntity<Long> objetoMonopatin = new HttpEntity<>(idMonopatin, cabecera);
        ResponseEntity<MonopatinDto> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8001/monopatines/" + idMonopatin,
                HttpMethod.DELETE,
                objetoMonopatin,
                new ParameterizedTypeReference<>() {}
        );
        cabecera.setContentType(MediaType.APPLICATION_JSON);

        return respuesta.getBody();
    }

    @Override
    public List<AdminDto> findAll() throws Exception {
        return null;
    }

    @Override
    public AdminDto findById(Long id) throws Exception {
        return repositorio.findById(id).map(AdminDto::new).orElseThrow(
                ()->new IllegalArgumentException("ID invalido:"+id)
        );
    }

    @Override
    public Object save(Object entity) throws Exception {
        return null;
    }

    /*
    @Override
    public AdminDto save(AdminDto adminDto) throws Exception {
        Administrador admin = new Administrador(adminDto.getId());
        Administrador aux = this.repositorio.save(admin);
        return new AdminDto(aux.getId());
    }
     */

}
