package com.example.demo.entity;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sucursales")
public class Sucursal {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSucursal;

    private String direccion;

    @OneToMany(mappedBy = "sucursal")
    private List<Vehiculo> vehiculos;

}
