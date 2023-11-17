package com.example.microusuarios.utils;

import com.example.microusuarios.entitys.Cuenta;
import com.example.microusuarios.entitys.Usuario;
import com.example.microusuarios.repositorios.CuentaRepositorio;
import com.example.microusuarios.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class DataUpload {

    private final CuentaRepositorio cuentarepositorio;
    private final UsuarioRepositorio usuariorepositorio;

    @Autowired
    public DataUpload(CuentaRepositorio cuentarepositorio,UsuarioRepositorio usuariorepositorio) {
        this.cuentarepositorio = cuentarepositorio;
        this.usuariorepositorio = usuariorepositorio;

    }

    public void loadData() throws IOException {

           Cuenta c1= new Cuenta(1L,false,"MercadoPago", null, 300);
           Cuenta c2= new Cuenta(2L,false,"MercadoPago45684", null, 250);

          Usuario u1= new Usuario(2L,"mati","nombre", 2234460201L, "mati@gmail.com");
          Usuario u2= new Usuario(202L,"juanchi","nombreusuario", 2234460201L, "juanchi@gmail.com");

          u2.setX(8L);
          u2.setY(9L);

          u1.setX(7L);
          u1.setY(6L);
          u1.addCuenta(c1);
          u1.addCuenta(c2);
          cuentarepositorio.save(c1);
          cuentarepositorio.save(c2);
          usuariorepositorio.save(u2);
          usuariorepositorio.save(u1);


    }

}
