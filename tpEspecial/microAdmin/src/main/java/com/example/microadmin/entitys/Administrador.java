package com.example.microadmin.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Administrador(Long id) {
        this.id = id;
    }
}
