package com.example.microadmin.controlers;

import com.example.microadmin.dtos.AdminDto;
import com.example.microadmin.dtos.MonopatinDto;
import com.example.microadmin.dtos.MonopatinIdDto;
import com.example.microadmin.servicios.AdminServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.microadmin.segurity.AuthorityConstants;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

@RestController
@RequestMapping("/administrador")
public class AdminControlador {

    @Autowired
    private AdminServicio service;


    @PostMapping("/monopatin")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> addMonopatin(@RequestBody MonopatinDto monopatin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.agregarMonopatin(monopatin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK

    @DeleteMapping("/monopatin/{idMonopatin}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> eliminarMonopatin(@PathVariable String idMonopatin){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.eliminarMonopatin(idMonopatin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK

    @PutMapping("/editarMonopatin")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> editarMonopatin(@RequestBody MonopatinIdDto m){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.editarMonopatin(m));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK

    @PutMapping("/editarMantenimiento/{idMonopatin}/habilitado/{estado}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> editarMantenimiento(@PathVariable String idMonopatin, @PathVariable boolean estado){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.editarMantenimiento(idMonopatin, estado));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK


    @PutMapping("/anularCuenta/{idCuenta}/estado/{estado}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> anularCuenta(@PathVariable Long idCuenta, @PathVariable boolean estado){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.anularCuenta(idCuenta, estado));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK


    @PutMapping ("/actualizarTarifas/tarifaNormal/{tarifaNormal}/TarifaPorPausa/{tarifaPorPausaExtensa}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> actualizarTarifas(@PathVariable Integer tarifaNormal, @PathVariable Integer tarifaPorPausaExtensa){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.actualizarTarifas(tarifaNormal, tarifaPorPausaExtensa));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK

    @GetMapping ("/totalFacturadoEntre/mes1/{mes1}/mes2/{mes2}/anio/{anio}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> totalFacturado(@PathVariable Integer mes1, @PathVariable Integer mes2, @PathVariable Integer anio){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.totalFacturado(mes1, mes2, anio));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK

    @GetMapping ("/id/{id}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> findById(@PathVariable Long id ){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    // PROBADO OK

    @GetMapping ("/tarifas")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> getAdmin(){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(service.getAdmin());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    // PROBADO OK

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> save(@RequestBody AdminDto a){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.save(a));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    // PROBADO OK

}
