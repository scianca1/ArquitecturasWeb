package dtos;

public class CarreraDto {
    private String nombre;
    private Double duracionAnios;
    public CarreraDto(String nombre, Double duracionAnios) {
        this.nombre = nombre;
        this.duracionAnios = duracionAnios;
    }
    public String getNombre() {
        return nombre;
    }
    public Double getDuracionAnios() {
        return duracionAnios;
    }
}