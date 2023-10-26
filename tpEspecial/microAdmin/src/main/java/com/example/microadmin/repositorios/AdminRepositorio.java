package com.example.microadmin.repositorios;

import com.example.microadmin.entitys.Administrador;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

public interface AdminRepositorio extends RepositorioBase <Administrador, Integer> {
}
