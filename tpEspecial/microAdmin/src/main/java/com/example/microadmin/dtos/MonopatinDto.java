package com.example.microadmin.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonopatinDto {

     private int x;
     private int y;
     private boolean habilitado;
     private double kmRecorridos;
     private double tiempoDeUso;
     private double tiempoDeUsoConPausa;


}
