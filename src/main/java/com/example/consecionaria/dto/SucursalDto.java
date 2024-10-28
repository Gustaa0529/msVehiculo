package com.example.consecionaria.dto;

import java.util.List;

import com.example.consecionaria.entity.Vehiculo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class SucursalDto {
	
	    private int idSucursal;
	    private String direccion;
	    private List<Vehiculo> vehiculos;
	    
	    public SucursalDto() {}

	    public SucursalDto(int idSucursal, String direccion, List<Vehiculo> vehiculos) {
	        this.idSucursal = idSucursal;
	        this.direccion = direccion;
	        this.vehiculos = vehiculos;
	    }
	    
}
