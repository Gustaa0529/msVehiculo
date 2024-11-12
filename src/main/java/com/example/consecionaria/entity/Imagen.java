package com.example.consecionaria.entity;

import com.example.consecionaria.dto.ImagenDto;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "imagenes")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 

    private String nombre;
    
    private String ruta;

    @Column(name = "idVehiculo", insertable = false, updatable = false) 
    private int idVehiculo;

    @ManyToOne // Establece la relación con Vehiculo
    @JoinColumn(name = "idVehiculo", referencedColumnName = "idVehiculo")
    private Vehiculo vehiculo;

    public Imagen() {
        super();
    }

    public Imagen(String nombre, String ruta, int idVehiculo) {
        super();
        this.nombre = nombre;
        this.ruta = ruta;
        this.idVehiculo = idVehiculo;
    }
    
    public ImagenDto toDto() {
        ImagenDto dto = new ImagenDto();
        dto.setNombre(this.getNombre());
        dto.setRuta(this.getRuta());
        dto.setIdVehiculo(this.getIdVehiculo());
        return dto;
    }
}
