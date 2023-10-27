package com.example.micromonopatines.controllers;

import com.example.micromonopatines.dtos.ParadaDto;
import com.example.micromonopatines.dtos.ParadaDtoConId;
import com.example.micromonopatines.servicios.ParadaServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paradas")
public class ParadaControlador {
    private ParadaServicio servicio;

    public ParadaControlador(ParadaServicio ps){
        this.servicio= ps;
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody ParadaDto p){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.save(p));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getParadas(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll());
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente mas tarde.\"}");
        }
    }

    @GetMapping("id/{idParada}")
    public ResponseEntity<?> findById(@PathVariable Long idParada){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(servicio.findById(idParada));
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
    public ResponseEntity<?> editar(@RequestBody ParadaDtoConId p){
        try{
            System.out.println(p.getMonopatines());
            System.out.println(p.getX());
            System.out.println(p.getY());
            System.out.println(p.getId());
            return ResponseEntity.status(HttpStatus.OK).body(servicio.put(p));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
}
