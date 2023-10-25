package com.example.microusuarios.servicios;

import com.example.microusuarios.dtos.UsuarioDto;
import com.example.microusuarios.entitys.Usuario;
import com.example.microusuarios.repositorios.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsuarioServicio implements BaseServicio<UsuarioDto> {

    private UsuarioRepositorio repositorio;

    public UsuarioServicio(UsuarioRepositorio ur){
        this.repositorio= ur;
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
}