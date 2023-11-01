package com.example.microadmin.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Administrador {

    @Id
    private Long id;

    @Column
    private Integer tarifa;

    @Column
    private Integer tarifaPorPausaExtensa;


}
