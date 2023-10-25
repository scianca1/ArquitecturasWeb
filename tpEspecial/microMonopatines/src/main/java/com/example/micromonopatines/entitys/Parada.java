package com.example.micromonopatines.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Parada")
@Getter
@Setter
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



    public Long getId() {
        return id;
    }
}
