package com.example.micromonopatines.repositorios;

import com.example.micromonopatines.entitys.Parada;
//import jakarta.transaction.Transactional;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParadaRepositorio extends  RepositorioBase<Parada, String>{
}
