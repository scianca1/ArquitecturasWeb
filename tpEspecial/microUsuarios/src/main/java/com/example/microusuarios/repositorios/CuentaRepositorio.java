package com.example.microusuarios.repositorios;

import com.example.microusuarios.entitys.Cuenta;
import com.example.microusuarios.entitys.Usuario;

import java.util.List;
import java.util.Optional;

public class CuentaRepositorio  implements RepositorioBase<Cuenta, Integer>{

    @Override
    public void delete(Cuenta c) {

    }

    @Override
    public List<Cuenta> findAll() {
        return null;
    }

    @Override
    public Optional<Cuenta> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Cuenta  save(Cuenta c) {
        return null;
    }
}
