package com.example.microusuarios.utils;

import com.example.microusuarios.controlers.UsuarioControlador;
import com.example.microusuarios.dtos.CuentaDto;
import com.example.microusuarios.dtos.UsuarioDto;
import com.example.microusuarios.entitys.Authority;
import com.example.microusuarios.entitys.Cuenta;
import com.example.microusuarios.entitys.Usuario;
import com.example.microusuarios.repositorios.AuthorityRepository;
import com.example.microusuarios.repositorios.CuentaRepositorio;
import com.example.microusuarios.repositorios.UsuarioRepositorio;
import com.example.microusuarios.servicios.CuentaServicio;
import com.example.microusuarios.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DataUpload {

    private final CuentaServicio cuentaServicio;
    private final AuthorityRepository authorityRepository;
    private final UsuarioControlador usuarioControlador;

    @Autowired
    public DataUpload(CuentaServicio cuentaServicio, UsuarioControlador usuarioControlador, AuthorityRepository authorityRepository) {
        this.cuentaServicio = cuentaServicio;
        this.usuarioControlador = usuarioControlador;
        this.authorityRepository = authorityRepository;
    }

    public void loadData() throws Exception {


           CuentaDto c1= new CuentaDto(new ArrayList<Usuario>(),false,"MercadoPago", null, 300);
           CuentaDto c2= new CuentaDto(new ArrayList<Usuario>(),false,"MercadoPago45684", null, 250);


           String nombre = "mati";
           String password = "1234";
           String nombreDeUsuario = "elmati";
           Long telefono = 2494856565L;
           String email = "elmati@hotmail.com";
           List<Long> cuentas = new ArrayList<Long>();
           cuentas.add(1L);
           List<String> authorities = new ArrayList<String>();
           authorities.add("ADMIN");
           UsuarioDto u1 = new UsuarioDto(1L, nombre, password, nombreDeUsuario, telefono, email, cuentas, authorities);

           String nombre2 = "flor";
           String password2 = "1234";
           String nombreDeUsuario2 = "laflor";
           Long telefono2 = 2494856565L;
           String email2 = "laflor@hotmail.com";
           List<Long> cuentas2 = new ArrayList<Long>();
           cuentas2.add(1L);
           List<String> authorities2 = new ArrayList<String>();
           authorities2.add("USER");
           UsuarioDto u2 = new UsuarioDto(1L, nombre2, password2, nombreDeUsuario2, telefono2, email2, cuentas2, authorities2);


           Authority authorityAdmin = new Authority("ADMIN");
           Authority authorityUser = new Authority("USER");
           this.authorityRepository.save(authorityAdmin);
           this.authorityRepository.save(authorityUser);
           this.cuentaServicio.save(c1);
           this.cuentaServicio.save(c2);
           this.usuarioControlador.register(u1);
           this.usuarioControlador.register(u2);
    }

}
