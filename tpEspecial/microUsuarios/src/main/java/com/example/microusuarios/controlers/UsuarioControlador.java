package com.example.microusuarios.controlers;

import com.example.microusuarios.dtos.UsuarioDto;
import com.example.microusuarios.servicios.UsuarioServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {
    private UsuarioServicio service;
    public UsuarioControlador(UsuarioServicio us){
        this.service= us;
    }
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody UsuarioDto c){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.save(c));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @GetMapping ("id/{idUsuario}")
    public ResponseEntity<?> findById(@PathVariable  Long idUsuario ){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(service.findById(idUsuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
}
