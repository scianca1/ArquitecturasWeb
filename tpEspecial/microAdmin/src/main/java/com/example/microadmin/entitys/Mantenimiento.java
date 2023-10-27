package com.example.microadmin.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long idMonopatin;

    @Temporal(TemporalType.DATE)    //No incluye fecha y hora (.TIMESTAMP si)
    private Date inicio;

    @Temporal(TemporalType.DATE)
    private Date fin;

    public void iniciarMantenimiento(){
        this.inicio = new Date();
    }

    public void finalizarMantenimiento(){
        this.fin = new Date();
    }
}
