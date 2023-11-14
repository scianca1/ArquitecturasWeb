package com.example.microusuarios;

import com.example.microusuarios.utils.DataUpload;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MicroUsuariosApplication {
    @Autowired
    private DataUpload data;

    public static void main(String[] args) {
        SpringApplication.run(MicroUsuariosApplication.class, args);
    }
    @PostConstruct
    public void init() throws IOException {
//		data.loadData();
    }
}
