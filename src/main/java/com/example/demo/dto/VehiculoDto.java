package com.example.demo.dto;

import java.util.List;
import lombok.Data;

@Data
public class VehiculoDto {
    private int idVehiculo;
    private String modelo;
    private int stock;
    private int precio;
    private SucursalDto sucursal; 
    private boolean mostrarVehiculo;
    private List<ImagenDto> imagenes; 

   
    public VehiculoDto() {
        super();
    }

  
    public VehiculoDto(String modelo, int stock, int precio, SucursalDto sucursal, boolean mostrarVehiculo, List<ImagenDto> imagenes) {
        super();
        this.modelo = modelo;
        this.stock = stock;
        this.precio = precio;
        this.sucursal = sucursal; 
        this.mostrarVehiculo = mostrarVehiculo;
        this.imagenes = imagenes;
    }

   
    public VehiculoDto(String modelo, int stock, int precio, int idsucursal, boolean mostrarVehiculo, List<ImagenDto> imagenes) {
        super();
        this.modelo = modelo;
        this.stock = stock;
        this.precio = precio;
        this.sucursal = new SucursalDto(idsucursal, null); // Creando un SucursalDto con solo idsucursal
        this.mostrarVehiculo = mostrarVehiculo;
        this.imagenes = imagenes;
    }
}
