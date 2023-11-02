package com.example.microusuarios.controlers;

import com.example.microusuarios.servicios.UsuarioServicio;
import com.example.microusuarios.dtos.UsuarioDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {
    private UsuarioServicio service;
    public UsuarioControlador(UsuarioServicio us){
        this.service= us;
    }
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody UsuarioDto c){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.save(c));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @GetMapping ("id/{idUsuario}")
    public ResponseEntity<?> findById(@PathVariable  Long idUsuario ){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(service.findById(idUsuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @PutMapping("/put")
    public ResponseEntity<?> editar(@RequestBody UsuarioDto u){
        try{
            //verificar que en el servicio pida el usuario y cargue las cuentas
            return ResponseEntity.status(HttpStatus.OK).body(service.put(u));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @PutMapping("/addCuenta/idUsuario/{id_usuario}/idCuenta/{id_cuenta}")
    public ResponseEntity<?> addCuenta(@PathVariable Long id_usuario,@PathVariable Long id_cuenta){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.addCuenta(id_usuario,id_cuenta));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @PutMapping("/removeCuenta/idUsuario/{id_usuario}/idCuenta/{id_cuenta}")
    public ResponseEntity<?> removeCuenta(@PathVariable Long id_usuario,@PathVariable Long id_cuenta){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.removeCuenta(id_usuario,id_cuenta));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @GetMapping("/IdUsuario/{idUsuario}/IdCuenta/{idCuenta}")
    public ResponseEntity<?> getCuentaDeUnUsuario(@PathVariable long idUsuario,@PathVariable long idCuenta){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.getCuentaDeUnUsuario(idUsuario,idCuenta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @GetMapping("/IdUsuario/{idUsuario}/cuentas")
    public ResponseEntity<?> getCuentaDeUnUsuario(@PathVariable long idUsuario){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.getCuentasDeUnUsuario(idUsuario));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @GetMapping("/{idUsuario}/monopatinesCercanos")
    public ResponseEntity<?> getMonopatinesCercanos(@PathVariable Long idUsuario){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.getMonopatinesCercanos(idUsuario));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente. "+"error= " +e);
        }
    }


}
