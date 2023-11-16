package com.example.microadmin.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @Column(nullable=false)
    private Integer tarifa;

    @Column(nullable=false)
    private Integer tarifaPorPausaExtensa;

    /*
    @Column
    private LocalDate fechaActualizacionTarifas;
     */


}
