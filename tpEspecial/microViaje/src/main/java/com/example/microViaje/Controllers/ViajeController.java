package com.example.microViaje.Controllers;

import com.example.microViaje.Services.ViajeService;
import com.example.microViaje.dtos.ViajeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viaje")
public class ViajeController {

        @Autowired
        private ViajeService service;

        public ViajeController(ViajeService s){
            this.service=s;
        }

        @GetMapping("")
        public ResponseEntity<?> getAll(){
            try{
                return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
            }
        }

        @GetMapping("id/{id}")
        public ResponseEntity<?> getById(@PathVariable Long id){
            try{
                return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
            }
        }
        @DeleteMapping("id/{id}")
        public ResponseEntity<?> delete(@PathVariable long id){
            try{
                service.delete(id);
                return ResponseEntity.status(HttpStatus.OK).body("Se elimino el viaje");
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar.\"\n\"error\":\"" + e.getMessage()+"\"}");
            }
        }

        @PutMapping("/editar/id/{id}")
        public ResponseEntity<?> update(@PathVariable long id, @RequestBody ViajeDto viajeDto){
        try{
            service.update(id, viajeDto);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PostMapping("/iniciarViaje")
        public ResponseEntity<?> iniciarViaje(@RequestBody ViajeDto viajeDto){
            try {
                System.out.println("Controler");
                return ResponseEntity.status(HttpStatus.OK).body(service.iniciarViaje(viajeDto));
            }
            catch(Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente."+e);
            }
        }

    @PutMapping("/finalizar/id/{id}")
    public ResponseEntity<?> finalizarViaje(@PathVariable long id){
        try{
            service.finalizarViaje(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se finalizo correctamente");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo finalizar.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PutMapping("/pausar/id/{id}")
    public ResponseEntity<?> pausarViaje(@PathVariable long id){
        try{
            service.pausarViaje(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se pauso correctamente el viaje");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo pausar.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PutMapping("/despausar/id/{id}")
    public ResponseEntity<?> despausarViaje(@PathVariable long id){
        try{
            service.despausarViaje(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se despauso correctamente el viaje");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo despausar.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    }


