package tpi3.tudai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpi3.tudai.dtos.CarrerDTO;
import tpi3.tudai.dtos.StudentCarrerDTO;
import tpi3.tudai.dtos.StudentDTO;
import tpi3.tudai.services.CarrerService;

@RestController
@RequestMapping("/carrers")
public class CarrerController {
	
	private CarrerService service;
	
	@Autowired
	public CarrerController(CarrerService service) {
		this.service = service;
	}

	@GetMapping("")
	public ResponseEntity<?> getCarrers(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente mas tarde.\"}");
		}
	}
	
	@GetMapping("/OrderByRegistered")
	public ResponseEntity<?> getCarrersOrderByRegistered(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findCarrersOrderByRegistered());
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente mas tarde.\"}");
		}
	}
	
	@PostMapping("/{id_carrer}/matricular")
	public ResponseEntity<?> matricular(@RequestBody StudentDTO dto, @PathVariable Integer id_carrer){
		return ResponseEntity.status(HttpStatus.OK).body(this.service.matricular(dto.getId(), id_carrer ));
	}	
	
	@GetMapping("/report")
	public ResponseEntity<?> getReport(){
		return ResponseEntity.status(HttpStatus.OK).body(this.service.getReport());
	}	
	
	@PostMapping("")	
	public ResponseEntity<?> save(@RequestBody CarrerDTO c){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.save(c));
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
		}
	}
	
	
	
}
