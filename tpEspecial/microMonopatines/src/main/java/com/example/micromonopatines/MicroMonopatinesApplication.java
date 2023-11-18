package com.example.micromonopatines;

import com.example.micromonopatines.utils.DataUpload;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MicroMonopatinesApplication {
    @Autowired
    private DataUpload data;
    public static void main(String[] args) {
        SpringApplication.run(MicroMonopatinesApplication.class, args);
    }

    @PostConstruct
    public void init() throws Exception {
        data.loadData();
    }
}
