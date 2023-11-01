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
    private int tarifa;

    @Column
    private int tarifaPorPausaExtensa;

    @Column
    private LocalDate fechaActualizacionPrecios;

    /*
    public void actualizarPrecios(int nuevaTarifa, int nuevaTarifaPorPausaExtensa) {
        if (fechaActualizacionPrecios.isAfter(LocalDate.now())) {
            this.setTarifa(nuevaTarifa);
            this.setTarifaPorPausaExtensa(nuevaTarifaPorPausaExtensa);
        } else {
            throw new IllegalArgumentException("La fecha de ajuste de precios debe ser en el futuro.");
        }
    }
     */

}
