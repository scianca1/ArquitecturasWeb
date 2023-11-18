package com.example.microadmin.controlers;


import com.example.microadmin.segurity.AuthorityConstants;
import com.example.microadmin.servicios.ReporteMonopatinServicio;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reporteMonopatin")
public class ReporteMonopatinControlador {

    @Autowired
    ReporteMonopatinServicio service;

    @GetMapping("/reportePorKm")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> reportePorKm (HttpServletRequest request){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getReportePorKm(request));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK

    @GetMapping("/reportePorTiempoSinPausas")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> reportePorTiempoSinPausa (HttpServletRequest request){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getReportePorTiempoSinPausa(request));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK

    @GetMapping("/reportePorTiempoConPausas")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> reportePorTiempoConPausa (HttpServletRequest request){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getReportePortiempoConPausa(request));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK

    @GetMapping("/reporteOperablesVsMantenimiento")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> reporteOperablesVsMantenimiento (HttpServletRequest request){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getReporteOperablesVsMantenimiento(request));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK

    @GetMapping ("/reporteCantidadViajes/{cant}/anio/{anio}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> getCantViajesMonopatinPorAnio(@PathVariable int cant, @PathVariable Integer anio, HttpServletRequest request){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(service.getCantViajesMonopatinPorAnio(cant, anio, request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    // PROBADO OK


}
