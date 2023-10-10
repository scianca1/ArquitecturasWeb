package tpi3.tudai.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tpi3.tudai.dtos.StudentDTO;
import tpi3.tudai.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService service;
	
	@GetMapping("")	
	public ResponseEntity<?> getStudents(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente mas tarde.\"}");
		}
	}
	
	@GetMapping("genre/{genero}")
    public ResponseEntity<?> getAllByGenero(@PathVariable String genero){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.findByGenero(genero));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
        }
    }
	
	@GetMapping("notebook/{libreta}")
    public ResponseEntity<?> getByNotebook(@PathVariable Integer libreta) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.findByNotebook(libreta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
        }
    }
	
	@GetMapping("/orderByName")
	public ResponseEntity<?> getAllOrderByName(){
		try{
            return ResponseEntity.status(HttpStatus.OK).body(service.findAllOrderByName());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
        }
	}
	
	@GetMapping("/carrerAndCity/{id}/{city}") 
	public ResponseEntity<?> getStudentsByCityAndCarrer(@PathVariable Integer id, @PathVariable String city){
		try{
            return ResponseEntity.status(HttpStatus.OK).body(service.findStudentsByCityAndCarrer(id, city));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\""+e.getMessage()+"\"}");
        }
	}
	
	@PostMapping("")	
	public ResponseEntity<?> save(@RequestBody StudentDTO s){

		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.save(s));
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
