package com.example.microadmin.controlers;

import com.example.microadmin.dtos.AdminDto;
import com.example.microadmin.dtos.MonopatinDto;
import com.example.microadmin.dtos.MonopatinIdDto;
import com.example.microadmin.servicios.AdminServicio;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
    public ResponseEntity<?> addMonopatin(@RequestBody MonopatinDto monopatin, HttpServletRequest request){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.agregarMonopatin(monopatin, request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK

    @DeleteMapping("/monopatin/{idMonopatin}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> eliminarMonopatin(@PathVariable String idMonopatin, HttpServletRequest request){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.eliminarMonopatin(idMonopatin, request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK

    @PutMapping("/editarMonopatin")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> editarMonopatin(@RequestBody MonopatinIdDto m, HttpServletRequest request){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.editarMonopatin(m, request));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK

    @PutMapping("/editarMantenimiento/{idMonopatin}/habilitado/{estado}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> editarMantenimiento(@PathVariable String idMonopatin, @PathVariable boolean estado, HttpServletRequest request){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.editarMantenimiento(idMonopatin, estado, request));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK


    @PutMapping("/anularCuenta/{idCuenta}/estado/{estado}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> anularCuenta(@PathVariable Long idCuenta, @PathVariable boolean estado, HttpServletRequest request){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.anularCuenta(idCuenta, estado, request));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK


    @PutMapping ("/actualizarTarifas/tarifaNormal/{tarifaNormal}/TarifaPorPausa/{tarifaPorPausaExtensa}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> actualizarTarifas(@PathVariable Integer tarifaNormal, @PathVariable Integer tarifaPorPausaExtensa, HttpServletRequest request){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.actualizarTarifas(tarifaNormal, tarifaPorPausaExtensa, request));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK

    @GetMapping ("/totalFacturadoEntre/mes1/{mes1}/mes2/{mes2}/anio/{anio}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> totalFacturado(@PathVariable Integer mes1, @PathVariable Integer mes2, @PathVariable Integer anio,HttpServletRequest request){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.totalFacturado(mes1, mes2, anio, request));
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
