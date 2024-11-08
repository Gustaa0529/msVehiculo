package com.example.consecionaria.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idvehiculo")
    private int idVehiculo;

    private String modelo;
    private int stock;
    private int precio;

    @ManyToOne
    @JoinColumn(name = "id_sucursal") // Clave foránea a la tabla sucursal
    private Sucursal sucursal;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Imagen> imagenes; // Lista de imágenes asociadas

    public Vehiculo() {
        super();
    }

    public Vehiculo(String modelo, int stock, int precio, Sucursal sucursal, List<Imagen> imagenes) {
        super();
        this.modelo = modelo;
        this.stock = stock;
        this.precio = precio;
        this.sucursal = sucursal;
        this.imagenes = imagenes;
    }

    public Vehiculo(int idVehiculo, String modelo, int stock, int precio, Sucursal sucursal, List<Imagen> imagenes) {
        super();
        this.idVehiculo = idVehiculo;
        this.modelo = modelo;
        this.stock = stock;
        this.precio = precio;
        this.sucursal = sucursal;
        this.imagenes = imagenes;
    }
}
