package com.example.microviaje.micromonopatines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
