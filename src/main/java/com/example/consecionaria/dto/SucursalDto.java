package com.example.consecionaria.dto;

import java.util.List;

import com.example.consecionaria.entity.Sucursal;
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
	    
	    public SucursalDto() {}

	    public SucursalDto(int idSucursal, String direccion) {
	        this.idSucursal = idSucursal;
	        this.direccion = direccion;
	    }
	    
	    public Sucursal toEntity() {
	        Sucursal sucursal = new Sucursal();
	        sucursal.setIdSucursal(this.idSucursal);
	        sucursal.setDireccion(this.direccion);
	        return sucursal;
	    }
	   
}
