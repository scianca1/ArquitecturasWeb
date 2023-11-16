package com.example.microusuarios.servicios;

import com.example.microusuarios.dtos.CuentaDto;
import com.example.microusuarios.dtos.MonopatinDto;
import com.example.microusuarios.ecxeptions.EnumUserException;
import com.example.microusuarios.ecxeptions.NotFoundException;
import com.example.microusuarios.ecxeptions.UserException;
import com.example.microusuarios.entitys.Cuenta;
import com.example.microusuarios.entitys.Usuario;
import com.example.microusuarios.repositorios.AuthorityRepository;
import com.example.microusuarios.repositorios.CuentaRepositorio;
import com.example.microusuarios.dtos.UsuarioDto;
import com.example.microusuarios.repositorios.UsuarioRepositorio;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service

@Transactional
@AllArgsConstructor
public class UsuarioServicio implements BaseServicio<UsuarioDto> {

    private  UsuarioRepositorio repositorio;

    private CuentaRepositorio repositoriocuenta;
    private AuthorityRepository authorityRepository;
    private PasswordEncoder passwordEncoder;
    private RestTemplate monopatinClienteRest;

    @Override
    public List<UsuarioDto> findAll() throws Exception {

        return  this.repositorio.findAll().stream().map(UsuarioDto::new).toList();
    }

    @Override
    public UsuarioDto findById(Long id) throws Exception {
        return repositorio.findById(id).map(UsuarioDto::new).orElseThrow(
                ()->new IllegalArgumentException("ID invalido:"+id)
        );
    }

    @Override
    public UsuarioDto save(UsuarioDto request) throws Exception {
        List<Cuenta> accounts= null;

        if( this.repositorio.existsUsersByEmailIgnoreCase( request.getEmail() ) )
            throw new UserException( EnumUserException.already_exist, String.format("Ya existe un usuario con email %s", request.getEmail() ) );
//        List<CuentaDto> aux = request.getCuentas();
        ArrayList<Long> ids = new ArrayList<Long>();
//        for (CuentaDto c: aux) {
//            ids.add(c.getId());
//        }
        if(request.getCuentas()!=null){
             accounts = this.repositoriocuenta.findAllById(request.getCuentas());
             if( accounts.isEmpty() )
                throw new UserException(EnumUserException.invalid_account,String.format("No se encontro ninguna cuenta con id %s", request.getCuentas().toString()));
        }

        final var authorities = request.getAuthorities()
                .stream()
                .map( string -> this.authorityRepository.findById( string ).orElseThrow( () -> new NotFoundException("Autority", string ) ) )
                .toList();
        if( authorities.isEmpty() )
            throw new UserException( EnumUserException.invalid_authorities,
                    String.format("No se encontro ninguna autoridad con id %s", request.getAuthorities().toString() ) );


        Long x=(long)Math.floor(Math.random()*10) ;
        Long y=(long)Math.floor(Math.random()*10) ;

        Usuario usuario = new Usuario( request.getNombre(), request.getNombreDeUsuario(),request.getTelefono(),request.getEmail());
        usuario.setX(x);
        usuario.setY(y);
        usuario.setAuthorities(authorities);
        usuario.setCuentas(accounts);
        final var encyptedPasswor = passwordEncoder.encode(request.getPassword());
        usuario.setPassword(encyptedPasswor);
        Usuario aux3 = this.repositorio.save(usuario);
        return new UsuarioDto(aux3);
    }

    @Override
    public UsuarioDto delete(Long id) throws Exception {
        Optional<Usuario> u= repositorio.findById(id);

        if (u.isPresent()) {
            Usuario usuario = u.get();
            UsuarioDto udto= new UsuarioDto(usuario);
            repositorio.delete(usuario);
            return  udto;
        }
        return null;
    }

    public UsuarioDto put(UsuarioDto usuarioDto)throws Exception {
        Optional<Usuario> opU=repositorio.findById(usuarioDto.getId());
        if(opU.isPresent()){
            Usuario usuario = opU.get();
            usuario.setNombreDeUsuario(usuarioDto.getNombreDeUsuario());
            usuario.setNombre(usuarioDto.getNombre());
            usuario.setEmail(usuarioDto.getEmail());
            usuario.setTelefono(usuarioDto.getTelefono());
            repositorio.save(usuario);
            return usuarioDto;
        }
        return null;


//                new Usuario(usuarioDto.getId(),usuarioDto.getNombre(), usuarioDto.getNombreDeUsuario(),usuarioDto.getTelefono(),usuarioDto.getEmail());
//        Optional<Cuenta> opC=repositoriocuenta.findById(1L);
//        Cuenta c= opC.get();
//        usuario.addCuenta(c);
//        usuario.removeCuenta(c);


    }
    public CuentaDto addCuenta(Long idUsuario, Long idCuenta)throws Exception {

        Optional<Usuario> opU=repositorio.findById(idUsuario);
        Optional<Cuenta> opC=repositoriocuenta.findById(idCuenta);

        if(opU.isPresent()&&opC.isPresent()){
          Cuenta cuenta=opC.get();
          Usuario u = opU.get();
          u.addCuenta(cuenta);
          CuentaDto cdto= new CuentaDto(cuenta);
          repositorio.save(u);
          return cdto;
        }
      return null;
    }
    public CuentaDto removeCuenta(Long idUsuario,Long idCuenta)throws Exception {

        Optional<Usuario> opU=repositorio.findById(idUsuario);
        Optional<Cuenta> opC=repositoriocuenta.findById(idCuenta);

        if(opU.isPresent()&&opC.isPresent()){
            Cuenta cuenta=opC.get();
            Usuario u = opU.get();
            u.removeCuenta(cuenta);
            CuentaDto cdto= new CuentaDto(cuenta);
            repositorio.save(u);
            return cdto;
        }
        return null;
    }

    public CuentaDto getCuentaDeUnUsuario(long idUsuario, long idCuenta) {
        Optional<Usuario> opU=repositorio.findById(idUsuario);
        Optional<Cuenta> opC=repositoriocuenta.findById(idCuenta);
        if(opU.isPresent()&&opC.isPresent()){
            Cuenta cuenta=opC.get();
            Usuario u = opU.get();
            if(u.getCuentas().contains(cuenta)){
                CuentaDto cdto= new CuentaDto(cuenta);
                return cdto;
            }
        }
        return null;
    }

    public List<CuentaDto> getCuentasDeUnUsuario(long idUsuario) {
        Optional<Usuario> opU=repositorio.findById(idUsuario);
        if(opU.isPresent()){
            Usuario u = opU.get();
            ArrayList<CuentaDto> cuentas=new ArrayList<CuentaDto>();
            for(Cuenta c:u.getCuentas()){
                cuentas.add(new CuentaDto(c));
            }
            return cuentas;
        }
        return null;
    }
    public List<MonopatinDto> getMonopatinesCercanos(Long idUsuario)throws Exception {
        Optional<Usuario> opU=repositorio.findById(idUsuario);
        HttpHeaders cabecera = new HttpHeaders();
        HttpEntity<MonopatinDto> objetoMonopatin = new HttpEntity<>(cabecera);
        ResponseEntity<List<MonopatinDto>> respuesta = monopatinClienteRest.exchange(
                "http://localhost:8001/monopatin",
                HttpMethod.GET,
                objetoMonopatin,
                new ParameterizedTypeReference<>(){}
        );
        cabecera.setContentType(MediaType.APPLICATION_JSON);

        if(opU.isPresent()&&respuesta.getStatusCode()==HttpStatus.OK){
            Usuario u= opU.get();
            Long usuarioX=u.getX();
            if(usuarioX<0){
                usuarioX=usuarioX*-1;
            }
            Long usuarioY=u.getY();
            if(usuarioY<0){
                usuarioY= usuarioY*-1;
            }

            List<MonopatinDto> lista = respuesta.getBody();
            Long rangoKMCercano = 2L;
            List<MonopatinDto> retorno = new ArrayList<>();
            for (MonopatinDto m:lista){

              Long MX= m.getX();
              Long MY= m.getY();
              if(usuarioX-MX<=rangoKMCercano && usuarioY-MY<=rangoKMCercano){
                  retorno.add(m);
              }
            }
            return retorno;
        }
        return  null;
    }


}