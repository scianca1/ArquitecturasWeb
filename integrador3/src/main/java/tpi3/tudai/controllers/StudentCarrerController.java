package tpi3.tudai.controllers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpi3.tudai.dtos.StudentCarrerDTO;
import tpi3.tudai.entities.StudentCarrer;
import tpi3.tudai.services.StudentCarrerService;

import java.util.Objects;

@RestController
@RequestMapping("/studentsCarrers")

public class StudentCarrerController {
	@Autowired
	private StudentCarrerService service;
	
	@GetMapping("")
	public ResponseEntity<?> getStudentsCarrers(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente mas tarde.\"}");
		}
	}
	
	@PostMapping("")	
	public ResponseEntity<?> save(@RequestBody StudentCarrerDTO sc){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.save(sc));
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, revise los campos e intente nuevamente.\"}");
		}
	}
	

}

