package com.example.microusuarios.controlers;

import com.example.microusuarios.dtos.UsuarioDto;
import com.example.microusuarios.dtos.request.AuthRequestDTO;
import com.example.microusuarios.security.Jwt.JWTFilter;
import com.example.microusuarios.security.Jwt.TokenProvider;
import com.example.microusuarios.servicios.UsuarioServicio;
import com.example.microusuarios.servicios.constant.AuthorityConstant;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioControlador {
    private final UsuarioServicio service;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @GetMapping ("/validate")
    public ResponseEntity<ValidateTokenDTO> validateGet() {
        final var user = SecurityContextHolder.getContext().getAuthentication();
        final var authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        return ResponseEntity.ok(
                ValidateTokenDTO.builder()
                        .username( user.getName() )
                        .authorities( authorities )
                        .isAuthenticated( true )
                        .build()
        );
    }
    @Data
    @Builder
    public static class ValidateTokenDTO {
        private boolean isAuthenticated;
        private String username;
        private List<String> authorities;
    }
    // INICIAR SESION
    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authenticate( @Valid @RequestBody AuthRequestDTO request ) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( request.getEmail(), request.getPassword() );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final var jwt = tokenProvider.createToken (authentication );
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add( JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt );
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioDto> register(@Valid @RequestBody UsuarioDto request ) throws Exception{
        final var newUser = this.service.save(request);
        return new ResponseEntity<>( newUser, HttpStatus.CREATED );
    }

    static class JWTToken {
        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }




//    @PostMapping("")
//    public ResponseEntity<?> save(@RequestBody UsuarioDto c){
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(service.save(c));
//        }
//        catch(Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
//        }
//    }
    @GetMapping ("id/{idUsuario}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstant.USER + "', '" + AuthorityConstant.ADMIN + "')")
    public ResponseEntity<?> findById(@PathVariable  Long idUsuario ){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(service.findById(idUsuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @GetMapping ("")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstant.ADMIN + "')")
    public ResponseEntity<?> findAll(){
        try{
            return  ResponseEntity.status(HttpStatus.OK).body(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstant.ADMIN + "')")
    public ResponseEntity<?> delete(@PathVariable long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.delete(id));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @PutMapping("/put")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstant.USER + "', '" + AuthorityConstant.ADMIN + "')")
    public ResponseEntity<?> editar(@RequestBody UsuarioDto u){
        try{
            //verificar que en el servicio pida el usuario y cargue las cuentas
            return ResponseEntity.status(HttpStatus.OK).body(service.put(u));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @PutMapping("/addCuenta/idUsuario/{id_usuario}/idCuenta/{id_cuenta}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstant.USER + "', '" + AuthorityConstant.ADMIN + "')")
    public ResponseEntity<?> addCuenta(@PathVariable Long id_usuario,@PathVariable Long id_cuenta){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.addCuenta(id_usuario,id_cuenta));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @PutMapping("/removeCuenta/idUsuario/{id_usuario}/idCuenta/{id_cuenta}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstant.USER + "', '" + AuthorityConstant.ADMIN + "')")
    public ResponseEntity<?> removeCuenta(@PathVariable Long id_usuario,@PathVariable Long id_cuenta){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.removeCuenta(id_usuario,id_cuenta));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @GetMapping("/IdUsuario/{idUsuario}/IdCuenta/{idCuenta}")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstant.USER + "', '" + AuthorityConstant.ADMIN + "')")
    public ResponseEntity<?> getCuentaDeUnUsuario(@PathVariable long idUsuario,@PathVariable long idCuenta){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.getCuentaDeUnUsuario(idUsuario,idCuenta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @GetMapping("/IdUsuario/{idUsuario}/cuentas")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstant.USER + "', '" + AuthorityConstant.ADMIN + "')")
    public ResponseEntity<?> getCuentaDeUnUsuario(@PathVariable long idUsuario){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.getCuentasDeUnUsuario(idUsuario));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }
    @GetMapping("/{idUsuario}/monopatinesCercanos")
    @PreAuthorize("hasAnyAuthority('" + AuthorityConstant.USER + "', '" + AuthorityConstant.ADMIN + "')")
    public ResponseEntity<?> getMonopatinesCercanos(@PathVariable Long idUsuario){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.getMonopatinesCercanos(idUsuario));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente. "+"error= " +e);
        }
    }


}
