package com.example.microadmin.servicios;

import com.example.microadmin.dtos.AdminDto;
import com.example.microadmin.dtos.CuentaDto;
import com.example.microadmin.dtos.MonopatinDto;
import com.example.microadmin.dtos.MonopatinIdDto;
import com.example.microadmin.entitys.Administrador;
import com.example.microadmin.repositorios.AdminRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AdminServicio implements BaseServicio<AdminDto>{

    private AdminRepositorio repositorio;

    @Autowired
    private RestTemplate monopatinClienteRest;

    public AdminServicio (AdminRepositorio ar){
        this.repositorio= ar;
    }

    public MonopatinIdDto editarMonopatin (MonopatinIdDto m){
        HttpHeaders cabecera = new HttpHeaders();
        HttpEntity<MonopatinIdDto> solicitud1 = new HttpEntity<>(m, cabecera);
        ResponseEntity<MonopatinIdDto> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8001/monopatines/put",
                HttpMethod.PUT,
                solicitud1,
                new ParameterizedTypeReference<>() {});
        cabecera.setContentType(MediaType.APPLICATION_JSON);

        return respuesta.getBody();
    }

    public MonopatinDto editarMantenimiento (Long idMonopatin, boolean estado){
        HttpHeaders cabecera = new HttpHeaders();
        HttpEntity<Void> solicitud1 = new HttpEntity<>(cabecera);
        ResponseEntity<MonopatinDto> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8001/monopatines/mantenimiento/" + idMonopatin + "/estado/" + estado,
                HttpMethod.PUT,
                solicitud1,
                new ParameterizedTypeReference<>() {});
        cabecera.setContentType(MediaType.APPLICATION_JSON);

        return respuesta.getBody();
    }

    public MonopatinDto agregarMonopatin(MonopatinDto monopatin) {
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

    public CuentaDto anularCuenta (Long id, boolean estado){
        HttpHeaders cabecera = new HttpHeaders();
        HttpEntity<CuentaDto> solicitud1 = new HttpEntity<>(cabecera);
        ResponseEntity<CuentaDto> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8003/cuenta/" + id + "/anular/" + estado,
                HttpMethod.PUT,
                solicitud1,
                new ParameterizedTypeReference<>() {});
        cabecera.setContentType(MediaType.APPLICATION_JSON);

        return respuesta.getBody();
    }

    public AdminDto actualizarTarifas(int tarifaNormal, int tarifaPorPausaExtensa){
        Administrador a= repositorio.actualizarTarifas(tarifaNormal, tarifaPorPausaExtensa);
        return new AdminDto(a);
    }

    public AdminDto getTarifas(){
        Administrador administrador= repositorio.getTarifas();
        return new AdminDto(administrador);
    }

    @Override
    public List<AdminDto> findAll() throws Exception {
        return null;
    }

    @Override
    public AdminDto findById(Long id) {
        return repositorio.findById(id).map(AdminDto::new).orElseThrow(
                ()->new IllegalArgumentException("ID invalido:"+id)
        );
    }

    @Override
    public AdminDto save(AdminDto adminDto) throws Exception {
        Administrador admin = new Administrador(adminDto.getId(), adminDto.getTarifa(), adminDto.getTarifaPorPausaExtensa());
        Administrador aux = this.repositorio.save(admin);
        return new AdminDto(aux.getId(), adminDto.getTarifa(), adminDto.getTarifaPorPausaExtensa());
    }


}
