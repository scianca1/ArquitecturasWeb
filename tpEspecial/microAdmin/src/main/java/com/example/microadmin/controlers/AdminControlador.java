package com.example.microadmin.controlers;

import com.example.microadmin.dtos.AdminDto;
import com.example.microadmin.dtos.MonopatinDto;
import com.example.microadmin.dtos.MonopatinIdDto;
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

    @PutMapping("/editarMonopatin")
    public ResponseEntity<?> editarMonopatin(@RequestBody MonopatinIdDto m){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.editarMonopatin(m));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    @PutMapping("/editarMantenimiento/{idMonopatin}/habilitado/{estado}")
    public ResponseEntity<?> editarMantenimiento(@PathVariable Long idMonopatin, @PathVariable boolean estado){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.editarMantenimiento(idMonopatin, estado));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    @PostMapping("/monopatines")
    public ResponseEntity<?> addMonopatin(@RequestBody MonopatinDto monopatin){
        try{
            System.out.println("acaservicio");
            return ResponseEntity.status(HttpStatus.OK).body(service.agregarMonopatin(monopatin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    @DeleteMapping("/monopatin/{idMonopatin}")
    public ResponseEntity<?> eliminarMonopatin(@PathVariable Long idMonopatin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.eliminarMonopatin(idMonopatin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    @PutMapping("/anularCuenta/{idCuenta}/estado/{estado}")
    public ResponseEntity<?> anularCuenta(@PathVariable Long idCuenta, @PathVariable boolean estado){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.anularCuenta(idCuenta, estado));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    @PutMapping ("/actualizarTarifas/{tarifaNormal}/{tarifaPorPausaExtensa}")
    public ResponseEntity<?> actualizarTarifas(@PathVariable int tarifaNormal, @PathVariable int tarifaPorPausaExtensa){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.actualizarTarifas(tarifaNormal, tarifaPorPausaExtensa));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

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

    @GetMapping ("/tarifas")
    public ResponseEntity<?> getTarifaConPausa(){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(service.getTarifas());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }

}
