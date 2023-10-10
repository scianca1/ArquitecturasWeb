package tpi3.tudai;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import tpi3.tudai.controllers.CarrerController;
import tpi3.tudai.dtos.StudentCarrerDTO;
import tpi3.tudai.repositories.StudentRepository;
import tpi3.tudai.utils.DataUpload;

@SpringBootApplication
public class TpIntegrador3Application {
	
	@Autowired
	private DataUpload data;

	public static void main(String[] args) {
		SpringApplication.run(TpIntegrador3Application.class, args);
	}
	
	
	@PostConstruct
	public void init() throws IOException {
		data.loadDataFromCSV();
	}
}
