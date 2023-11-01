package com.example.microusuarios.servicios;

import com.example.microusuarios.dtos.CuentaDto;
import com.example.microusuarios.entitys.Cuenta;
import com.example.microusuarios.entitys.Usuario;
import com.example.microusuarios.repositorios.CuentaRepositorio;
import com.example.microusuarios.dtos.UsuarioDto;
import com.example.microusuarios.repositorios.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UsuarioServicio implements BaseServicio<UsuarioDto> {

    private UsuarioRepositorio repositorio;
    private CuentaRepositorio repositoriocuenta;

    public UsuarioServicio(UsuarioRepositorio ur, CuentaRepositorio cr){
        this.repositorio= ur;
        this.repositoriocuenta=cr;

    }
    @Override
    public List<UsuarioDto> findAll() throws Exception {
        return null;
    }

    @Override
    public UsuarioDto findById(Long id) throws Exception {
        return repositorio.findById(id).map(UsuarioDto::new).orElseThrow(
                ()->new IllegalArgumentException("ID invalido:"+id)
        );
    }

    @Override
    public UsuarioDto save(UsuarioDto usuarioDto) throws Exception {
        Usuario usuario = new Usuario( usuarioDto.getNombre(), usuarioDto.getNombreDeUsuario(),usuarioDto.getTelefono(),usuarioDto.getEmail());
        Usuario aux = this.repositorio.save(usuario);
        return new UsuarioDto(aux.getNombre(), aux.getNombreDeUsuario(),aux.getTelefono(),aux.getEmail());
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
}