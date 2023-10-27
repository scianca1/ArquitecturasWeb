package com.example.micromonopatines;

import com.example.micromonopatines.servicios.MonopatinServicio;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MicroMonopatinesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroMonopatinesApplication.class, args);
    }

//    @PostConstruct
//    public void init() throws IOException {
//        MonopatinServicio servicio= new MonopatinServicio();
//        servicio.probando(1L);
//    }
}
