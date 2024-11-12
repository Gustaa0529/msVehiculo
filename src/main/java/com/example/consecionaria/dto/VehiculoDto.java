package com.example.consecionaria.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.example.consecionaria.entity.Imagen;
import com.example.consecionaria.entity.Vehiculo;

import lombok.Data;

@Data
public class VehiculoDto {
	
    private int idVehiculo;
    private String modelo;
    private int stock;
    private int precio;
    private SucursalDto sucursal; 
    private List<ImagenDto> imagenes; 

   
    public VehiculoDto() {
        super();
    }

  
    public VehiculoDto(String modelo, int stock, int precio, SucursalDto sucursal, List<ImagenDto> imagenes) {
        super();
        this.modelo = modelo;
        this.stock = stock;
        this.precio = precio;
        this.sucursal = sucursal; 
        this.imagenes = imagenes;
    }

   
    public VehiculoDto(String modelo, int stock, int precio, int idsucursal, List<ImagenDto> imagenes) {
        super();
        this.modelo = modelo;
        this.stock = stock;
        this.precio = precio;
        this.sucursal = new SucursalDto(idsucursal, null); // Creando un SucursalDto con solo idsucursal
        this.imagenes = imagenes;
    }
    
    public Vehiculo toEntity() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setIdVehiculo(this.idVehiculo);
        vehiculo.setModelo(this.modelo);
        vehiculo.setStock(this.stock);
        vehiculo.setPrecio(this.precio);
        if (this.sucursal != null) {
            vehiculo.setSucursal(this.sucursal.toEntity());
        }
        if (this.imagenes != null) {
            vehiculo.setImagenes(this.imagenes.stream()
                    .map(ImagenDto::toEntity)
                    .collect(Collectors.toList()));
        }
        return vehiculo;
    }
    
    
}
