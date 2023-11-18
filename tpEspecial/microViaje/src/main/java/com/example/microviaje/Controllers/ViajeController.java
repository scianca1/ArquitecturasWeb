package com.example.microviaje.Controllers;

import com.example.microviaje.Services.ViajeService;
import com.example.microviaje.dtos.ViajeDto;
import com.example.microviaje.dtos.ViajeRequestDto;
import com.example.microviaje.security.AuthorityConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @GetMapping("id/{id}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @GetMapping("anio/{anio}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> getViajeByAnio(@PathVariable Integer anio){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.viajesByAnio(anio));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    @GetMapping("viajesPorAnioEntreMeses/anio/{anio}/mes1/{mes1}/mes2/{mes2}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> getViajesEntreMeses(@PathVariable Integer anio,@PathVariable Integer mes1, @PathVariable Integer mes2){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.viajesPorAñoEntreMeses(anio,mes1,mes2));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Intente nuevamente.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }
    @DeleteMapping("id/{id}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> delete(@PathVariable long id){
        try{
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino el viaje");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PutMapping("/id/{id}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "')")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody ViajeDto viajeDto){
        try{
            service.update(id, viajeDto);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo correctamente");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo actualizar.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PostMapping("/inicioViaje")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "', '"+AuthorityConstants.USER+"')")
    public ResponseEntity<?> iniciarViaje(@RequestBody ViajeRequestDto viajeDto, HttpServletRequest request){
        try {
            System.out.println("Controler");
            return ResponseEntity.status(HttpStatus.OK).body(service.iniciarViaje(viajeDto, request));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente."+e);
        }
    }

    @PutMapping("/finalizacion/id/{id}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "', '"+AuthorityConstants.USER+"')")
    public ResponseEntity<?> finalizarViaje(@PathVariable Long id, HttpServletRequest request){
        try{
            service.finalizarViaje(id, request);
            return ResponseEntity.status(HttpStatus.OK).body("Se finalizo correctamente");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo finalizar.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PutMapping("/pausa/id/{id}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "', '"+AuthorityConstants.USER+"')")
    public ResponseEntity<?> pausarViaje(@PathVariable long id, HttpServletRequest request){
        try{
            service.pausarViaje(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se pauso correctamente el viaje");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo pausar.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    @PutMapping("/despausar/id/{id}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstants.ADMIN + "', '"+AuthorityConstants.USER+"')")
    public ResponseEntity<?> despausarViaje(@PathVariable long id){
        try{
            service.despausarViaje(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se despauso correctamente el viaje");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo despausar.\"\n\"error\":\"" + e.getMessage()+"\"}");
        }
    }

    }


