package com.example.microadmin.controlers;


import com.example.microadmin.dtos.reporteDto.ReporteDeUsoPorKm;
import com.example.microadmin.servicios.ReporteMonopatinServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reporteMonopatin")
public class ReporteMonopatinControlador {

    @Autowired
    ReporteMonopatinServicio service;

    @GetMapping("/reportePorKm")
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
    public ResponseEntity<?> reporteOperablesVsMantenimiento (){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getReporteOperablesVsMantenimiento());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    // PROBADO OK


}
