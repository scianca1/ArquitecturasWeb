package com.example.microadmin.controlers;


import com.example.microadmin.servicios.ReporteMonopatinServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reporteMonopatin")
public class ReporteMonopatinControlador {

    @Autowired
    ReporteMonopatinServicio reporte;

    /*
    @PutMapping("/modificarReporte/{idMonopatin}")
    public String modificarReporte (@RequestBody ){
        return null;
    }
     */


}
