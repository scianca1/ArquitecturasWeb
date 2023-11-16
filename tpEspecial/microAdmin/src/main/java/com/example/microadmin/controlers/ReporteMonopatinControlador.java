package com.example.microadmin.controlers;


import com.example.microadmin.segurity.AuthorityConstants;
import com.example.microadmin.servicios.ReporteMonopatinServicio;
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
    public ResponseEntity<?> reportePorKm (){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getReportePorKm());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK

    @GetMapping("/reportePorTiempoSinPausas")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> reportePorTiempoSinPausa (){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getReportePorTiempoSinPausa());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK

    @GetMapping("/reportePorTiempoConPausas")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> reportePorTiempoConPausa (){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getReportePortiempoConPausa());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK

    @GetMapping("/reporteOperablesVsMantenimiento")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> reporteOperablesVsMantenimiento (){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getReporteOperablesVsMantenimiento());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK

    @GetMapping ("/reporteCantidadViajes/{cant}/anio/{anio}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> getCantViajesMonopatinPorAnio(@PathVariable int cant, @PathVariable Integer anio){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(service.getCantViajesMonopatinPorAnio(cant, anio));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    // PROBADO OK


}
