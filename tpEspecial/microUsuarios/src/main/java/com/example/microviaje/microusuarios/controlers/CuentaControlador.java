package com.example.microviaje.microusuarios.controlers;

import com.example.microviaje.microusuarios.dtos.CuentaDto;
import com.example.microviaje.microusuarios.servicios.CuentaServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/{idCuenta}/anular/{anulada}")
    public ResponseEntity<?> setAnulada(@PathVariable long idCuenta,@PathVariable boolean anulada ){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.setAnulada(idCuenta,anulada));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }

}
