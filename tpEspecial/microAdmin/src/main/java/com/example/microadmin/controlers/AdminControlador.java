package com.example.microadmin.controlers;

import com.example.microadmin.dtos.AdminDto;
import com.example.microadmin.dtos.MonopatinDto;
import com.example.microadmin.servicios.AdminServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administrador")
public class AdminControlador {

    @Autowired
    private AdminServicio service;

    @PostMapping("/iniciarMantenimiento/{idMonopatin}")
    public ResponseEntity<?> iniciarMantenimiento(@PathVariable Long idMonopatin){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.iniciarMantenimiento(idMonopatin));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    @PutMapping("/finalizarMantenimiento/{idMonopatin}")
    public ResponseEntity<?> finalizarMantenimiento(@PathVariable Long idMonopatin){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.finalizarMantenimiento(idMonopatin));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    @PostMapping("/monopatines")
    public ResponseEntity<?> addMonopatin(@RequestBody MonopatinDto monopatin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.addMonopatin(monopatin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    @DeleteMapping("/monopatines/{idMonopatin}")
    public ResponseEntity<?> eliminarMonopatin(@PathVariable Long idMonopatin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.eliminarMonopatin(idMonopatin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    /*
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody AdminDto a){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.save(a));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }

    @GetMapping ("id/{id}")
    public ResponseEntity<?> findById(@PathVariable  Long id ){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }

     */
}
