package com.example.micromonopatines.servicios;

import com.example.micromonopatines.dtos.UsuarioDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MonopatinServicio {

    private RestTemplate rest;
    public MonopatinServicio() {
        rest= new RestTemplate();
    }
    public void  probando(Long usuarioId){
        ResponseEntity<UsuarioDto> respuesta=this.rest.getForEntity("http://localhost:8080/usuario/id/"+usuarioId, UsuarioDto.class);
        UsuarioDto usuario=respuesta.getBody();
        System.out.println(usuario.getNombre());
    }


}
