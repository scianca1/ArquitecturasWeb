package com.example.microviaje.microadmin.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonopatinDto {

     private Long x;
     private Long y;
     private boolean habilitado;
     private Long kmRecorridos;
     private double tiempoDeUso;
     private double tiempoDeUsoConPausa;


}
