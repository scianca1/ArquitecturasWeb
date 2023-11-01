package com.example.microadmin.dtos;

import com.example.microadmin.entitys.Administrador;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto {
    private Long id;
    private int tarifa;
    private int tarifaPorPausaExtensa;

    public AdminDto(Long id, int tarifa, int tarifaPorPausaExtensa) {
        this.id = id;
        this.tarifa = tarifa;
        this.tarifaPorPausaExtensa = tarifaPorPausaExtensa;
    }
    public AdminDto(int tarifa, int tarifaPorPausaExtensa) {
        this.tarifa = tarifa;
        this.tarifaPorPausaExtensa = tarifaPorPausaExtensa;
    }

    public AdminDto(Long id) {
        this.id= id;
    }

    public AdminDto(Administrador a){
        this.tarifa= a.getTarifa();
        this.tarifaPorPausaExtensa= a.getTarifaPorPausaExtensa();
    }


}
