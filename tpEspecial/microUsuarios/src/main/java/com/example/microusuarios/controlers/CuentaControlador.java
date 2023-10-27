package com.example.microusuarios.controlers;

import com.example.microusuarios.dtos.CuentaDto;
import com.example.microusuarios.dtos.UsuarioDto;
import com.example.microusuarios.servicios.CuentaServicio;
import com.example.microusuarios.servicios.UsuarioServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cuenta")
public class CuentaControlador {

    private CuentaServicio service;

    public CuentaControlador(CuentaServicio cs){
        this.service= cs;
    }
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody CuentaDto c){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.save(c));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }

}
