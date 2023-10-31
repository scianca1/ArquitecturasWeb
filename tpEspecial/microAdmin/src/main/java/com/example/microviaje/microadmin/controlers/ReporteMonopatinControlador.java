package com.example.microviaje.microadmin.controlers;


import com.example.microviaje.microadmin.servicios.ReporteMonopatinServicio;
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

    @GetMapping("/reportePorTiempoSinPausas")
    public ResponseEntity<?> reportePorTiempoSinPausa (){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getReportePorTiempoSinPausa());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    @GetMapping("/reportePorTiempoConPausas")
    public ResponseEntity<?> reportePorTiempoConPausa (){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getReportePortiempoConPausa());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }



}
