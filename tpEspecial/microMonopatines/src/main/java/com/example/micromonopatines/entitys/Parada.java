package com.example.micromonopatines.entitys;

import com.example.micromonopatines.dtos.MonopatinDtoConId;
import com.example.micromonopatines.dtos.ParadaDto;
import com.example.micromonopatines.dtos.ParadaDtoConId;
//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Document(collection = "paradas")
@Getter
@Setter
public class Parada implements Serializable {

    @Id
    private String id;
    private Long x;
    private Long y;
    List<String> monopatines;

    public Parada(Long x, Long y) {
        this.x = x;
        this.y = y;
        this.monopatines = new ArrayList<>();
    }
    public Parada() {
        this.monopatines = new ArrayList<>();
    }

    public Parada(ParadaDto paradaDto){
        this.x = paradaDto.getX();
        this.y = paradaDto.getY();
        this.monopatines = new ArrayList<>();
        for (String m: paradaDto.getMonopatines()) {
            this.monopatines.add(m);
        }
    }
    public Parada(ParadaDtoConId paradaDto){
        this.id = paradaDto.getId();
        this.x = paradaDto.getX();
        this.y = paradaDto.getY();
        if(paradaDto.getMonopatines().size() != 0){
            this.monopatines = new ArrayList<String>();
            for (String m: paradaDto.getMonopatines()) {
                this.monopatines.add(m);
            }
        }
        else{
            this.monopatines = new ArrayList<String>();
        }
    }



    public String getId() {
        return id;
    }

    public void addMonopatin(String monoId) {
//        if (this.monopatines == null){
//            this.monopatines = new ArrayList<String>();
//        }
        this.monopatines.add(monoId);
    }
}
