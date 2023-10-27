package com.example.Services;

import com.example.Repositories.ViajeRepository;
import com.example.dtos.MonopatinDto;
import com.example.dtos.UsuarioDto;
import com.example.dtos.ViajeDto;
import com.example.entitys.Viaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@Service
public class ViajeService {

    @Autowired
    private ViajeRepository repositorio;

    private RestTemplate rest;
    public ViajeService() {
        rest= new RestTemplate();
    }

    public List<ViajeDto> findAll() throws Exception {
        return this.repositorio.findAll().stream().map(ViajeDto::new).toList();
    }

    public ViajeDto findById(Long id) throws Exception {
        return this.repositorio.findById(id).map(ViajeDto::new).orElseThrow(
                () -> new IllegalArgumentException("ID de estacion invalido: " + id));
    }

    public ViajeDto save(Long usuarioId, Long monopatinId) throws Exception {
        ResponseEntity<UsuarioDto> usuario=this.rest.getForEntity("http://localhost:8003/usuario/id/"+usuarioId, UsuarioDto.class);
        //Ver como poner cuenta
        ResponseEntity<MonopatinDto> monopatin = rest.getForEntity("http://localhost:8001/monopatin/id" + monopatinId, MonopatinDto.class);
        if (usuario.getStatusCode() != HttpStatus.OK || monopatin.getStatusCode()  != HttpStatus.OK) {
            throw new IllegalArgumentException("id de usuario o monopatin invalido");
        }

        //VER ATRIBUTOS
        Viaje viaje= new Viaje(usuarioId, monopatinId, );
        ViajeDto respuesta= new ViajeDto(repositorio.save(viaje));
        return respuesta;
    }

    public void update(Long id, ViajeDto viajeDto) {
        Viaje viaje = repositorio.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id invalido"));
        viaje.setIdUsuario(viajeDto.getIdUsuario());
        viaje.setIdMonopatin(viajeDto.getIdMonopatin());
        viaje.setPausa(viajeDto.getPausa());
        viaje.setFechaInicio(viajeDto.getFechaInicio());
        viaje.setFechaFin(viajeDto.getFechaFin());
        viaje.setHoraFin(viajeDto.getHoraFin());
        viaje.setHoraInicio((viajeDto.getHoraInicio()));
        repositorio.save(viaje);
    }


    public void delete(Long id) {
        repositorio.delete(repositorio.findById(id).orElseThrow(
                () -> new IllegalArgumentException("id invalido")));
    }
}