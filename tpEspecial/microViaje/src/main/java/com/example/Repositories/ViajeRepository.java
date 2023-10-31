package com.example.Repositories;

import com.example.entitys.Viaje;

import java.util.List;
import java.util.Optional;

public class ViajeRepository implements BaseRepository<Viaje, Integer> {
    @Override
    public void delete(Viaje deleted) {

    }

    @Override
    public List<Viaje> findAll() {
        return null;
    }

    @Override
    public Optional<Viaje> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {

    }

}
