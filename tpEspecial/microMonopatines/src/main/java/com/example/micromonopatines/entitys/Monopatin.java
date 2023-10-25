package com.example.micromonopatines.entitys;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "Monopatin")
@Getter
@Setter
public class Monopatin implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column
    private Long x;
    @Column
    private Long y;
    @Column
    private boolean habilitado;
    @Column
    private long kmRecorridos;
    @Column
    private double tiempoDeUso;

    public Monopatin() {
    }

    public Monopatin(Long x, Long y, boolean habilitado, long kmRecorridos, double tiempoDeUso) {
        this.x = x;
        this.y = y;
        this.habilitado = habilitado;
        this.kmRecorridos = kmRecorridos;
        this.tiempoDeUso = tiempoDeUso;
    }


    public Long getId() {
        return id;
    }
}
