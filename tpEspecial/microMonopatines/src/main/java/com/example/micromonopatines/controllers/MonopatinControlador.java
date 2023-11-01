package com.example.micromonopatines.controllers;

import com.example.micromonopatines.dtos.MonopatinDto;
import com.example.micromonopatines.dtos.MonopatinDtoConId;
import com.example.micromonopatines.servicios.MonopatinServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monopatin")
public class MonopatinControlador{
    private MonopatinServicio servicio;

    public MonopatinControlador(MonopatinServicio ms){
        this.servicio= ms;
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody MonopatinDto m){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.save(m));
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

    @GetMapping("id/{idMonopatin}")
    public ResponseEntity<?> findById(@PathVariable Long idMonopatin){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(servicio.findById(idMonopatin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.servicio.delete(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @PutMapping("/put")
    public ResponseEntity<?> editar(@RequestBody MonopatinDtoConId m){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.put(m));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @PutMapping("/id/{id}/habilitado/{isHabilitado}")
    public ResponseEntity<?> habilitar(@PathVariable Long id, @PathVariable boolean isHabilitado){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.habilitar(id, isHabilitado));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }

    @PutMapping("/addKilometros/id/{id}/km/{km}")
    public ResponseEntity<?> addKmRecorridos(@PathVariable Long id, @PathVariable double km){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.addKmRecorridos(id, km));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
}
