package com.example.microadmin;

import com.example.microadmin.utils.DataUpload;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MicroAdminApplication {
    @Autowired
    private DataUpload data;

    public static void main(String[] args) {
        SpringApplication.run(MicroAdminApplication.class, args);
    }

    @PostConstruct
    public void init() throws IOException {
//        data.loadData();
    }

}
