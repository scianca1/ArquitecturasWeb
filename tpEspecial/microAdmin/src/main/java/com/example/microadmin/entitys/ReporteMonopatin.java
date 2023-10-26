package com.example.microadmin.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteMonopatin {

    @Id
    @Column(name = "id_monopatin")
    private Long id;

    private double tiempoUsadoSinPausa;

    private double tiempoUsadoConPausa;

    private Long kmRecorridos;

    private int viajesTotales;

}
