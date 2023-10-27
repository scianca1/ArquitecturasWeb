package com.example.Controllers;

import com.example.Services.ViajeService;
import com.example.dtos.ViajeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viaje")
public class ViajeController {

        @Autowired
        private ViajeService service;

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

        @PutMapping("id/{id}")
        public ResponseEntity<?> update(@PathVariable long id, @RequestBody ViajeDto viajeDto){
        try{
            service.update(id, viajeDto);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PostMapping("usuario/{usuarioId}/monopatin/{monoparinId}")
        public ResponseEntity<?> save(@RequestBody Long usuarioId, Long monopatinId, Long cuentaId,Long paradaOrigenId, Long paradaDestinoId){
            try {
                return ResponseEntity.status(HttpStatus.OK).body(service.save(usuarioId,monopatinId, cuentaId,paradaOrigenId,paradaDestinoId));
            }
            catch(Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
            }
        }

    @PutMapping("id/{id}")
    public ResponseEntity<?> finalizarViaje(@PathVariable long id){
        try{
            service.finalizarViaje(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se finalizo correctamente");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo finalizar.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    }


