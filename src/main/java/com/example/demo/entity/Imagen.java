package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "imagenes") // Nombre de la tabla en la base de datos
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Clave primaria autogenerada
    private Long id; // Campo ID como clave primaria

    private String nombre;

    @Lob // Indica que este campo puede ser grande
    private byte[] contenido; // Almacena el contenido de la imagen en formato binario

    @Column(name = "idVehiculo", insertable = false, updatable = false) // Evita que JPA maneje este campo
    private int idVehiculo; // Referencia al vehículo asociado

    @ManyToOne // Establece la relación con Vehiculo
    @JoinColumn(name = "idVehiculo", referencedColumnName = "idVehiculo") // Configura la clave foránea
    private Vehiculo vehiculo;

    public Imagen() {
        super();
    }

    public Imagen(String nombre, byte[] contenido, int idVehiculo) {
        super();
        this.nombre = nombre;
        this.contenido = contenido;
        this.idVehiculo = idVehiculo;
    }
    
}
