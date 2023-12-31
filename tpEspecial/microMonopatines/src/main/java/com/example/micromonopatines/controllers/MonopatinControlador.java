package com.example.micromonopatines.controllers;

import com.example.micromonopatines.dtos.MonopatinDto;
import com.example.micromonopatines.dtos.MonopatinDtoConId;
import com.example.micromonopatines.security.AuthorityConstants;
import com.example.micromonopatines.servicios.MonopatinServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monopatin")
public class MonopatinControlador{
    private MonopatinServicio servicio;

    public MonopatinControlador(MonopatinServicio ms){
        this.servicio= ms;
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> save(@RequestBody MonopatinDto m){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.save(m));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "', '"+AuthorityConstants.USER+"')")
    public ResponseEntity<?> getMonopatines(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findAll());
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("id/{idMonopatin}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "', '"+AuthorityConstants.USER+"')")
    public ResponseEntity<?> findById(@PathVariable String idMonopatin){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(servicio.findById(idMonopatin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> delete(@PathVariable String id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(this.servicio.delete(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @PutMapping("")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> editar(@RequestBody MonopatinDtoConId m){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.put(m));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @PutMapping("/id/{id}/estado/{isHabilitado}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> habilitar(@PathVariable String id, @PathVariable boolean isHabilitado){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.habilitar(id, isHabilitado));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }

    @PutMapping("/id/{id}/km/{km}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> addKmRecorridos(@PathVariable String id, @PathVariable double km){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.addKmRecorridos(id, km));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
}
