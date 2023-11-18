package com.example.microusuarios.controlers;

import com.example.microusuarios.dtos.CuentaDto;
import com.example.microusuarios.servicios.CuentaServicio;
import com.example.microusuarios.servicios.constant.AuthorityConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PutMapping("/idCuenta/{idCuenta}/estado/{anulada}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstant.ADMIN + "')")
    public ResponseEntity<?> setAnulada(@PathVariable long idCuenta,@PathVariable boolean anulada ){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.setAnulada(idCuenta,anulada));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }

}
