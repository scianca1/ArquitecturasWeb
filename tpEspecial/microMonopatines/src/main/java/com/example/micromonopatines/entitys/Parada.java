package com.example.micromonopatines.entitys;

import com.example.micromonopatines.dtos.MonopatinDtoConId;
import com.example.micromonopatines.dtos.ParadaDto;
import com.example.micromonopatines.dtos.ParadaDtoConId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "Parada")
@Getter
@Setter
@NoArgsConstructor
public class Parada implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column
    private Long x;
    @Column
    private Long y;
    @OneToMany
    List<Monopatin> monopatines;

    public Parada(Long x, Long y) {
        this.x = x;
        this.y = y;
    }

    public Parada(ParadaDto paradaDto){
        this.x = paradaDto.getX();
        this.y = paradaDto.getY();
        for (MonopatinDtoConId m: paradaDto.getMonopatines()) {
            this.monopatines.add(new Monopatin(m));
        }
    }
    public Parada(ParadaDtoConId paradaDto){
        this.id = paradaDto.getId();
        this.x = paradaDto.getX();
        this.y = paradaDto.getY();
        if(paradaDto.getMonopatines().size() != 0){
            for (MonopatinDtoConId m: paradaDto.getMonopatines()) {
                this.monopatines.add(new Monopatin(m));
            }
        }
        else{
            this.monopatines = new ArrayList<Monopatin>();
        }
    }



    public Long getId() {
        return id;
    }

    public void addMonopatin(Monopatin m) {
        this.monopatines.add(m);
    }
}
