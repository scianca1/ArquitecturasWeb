package com.example.microadmin.dtos;

import com.example.microadmin.entitys.Administrador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    private Long id;

    public AdminDto(Long id) {
        this.id = id;
    }

    public AdminDto(Administrador administrador) {
        this.id= administrador.getId();
    }
}
