package com.example.microadmin.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class ActualizarTarifas {
    @Id
    @Column
    private Long id;

    @Column
    private Integer tarifaActualizada;

    @Column
    private Integer tarifaPorPausaExtensaActualizada;

    @Column
    private LocalDate fechaActualizacion;

    public boolean chequearActualizaciones(){
        return (this.fechaActualizacion.isEqual(LocalDate.now()) || this.fechaActualizacion.isBefore(LocalDate.now()));
    }

}
