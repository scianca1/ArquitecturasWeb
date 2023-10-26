package com.example.micromonopatines.controllers;

import com.example.micromonopatines.dtos.MonopatinDto;
import com.example.micromonopatines.servicios.MonopatinServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monopatin")
public class MonopatinControlador{
    private MonopatinServicio servicio;

    public MonopatinControlador(MonopatinServicio us){
        this.servicio= us;
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody MonopatinDto c){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.save(c));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getMonopatines(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll());
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente mas tarde.\"}");
        }
    }

    @GetMapping("id/{idUsuario}")
    public ResponseEntity<?> findById(@PathVariable Long idUsuario){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(servicio.findById(idUsuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
}
