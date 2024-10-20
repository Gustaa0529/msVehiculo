package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class SucursalDto {
	
	    private int idSucursal;
	    private String direccion;

	    // Constructor vacío
	    public SucursalDto() {
	        super();
	    }

	    // Constructor con parámetros
	    public SucursalDto(int idSucursal, String direccion) {
	        super();
	        this.idSucursal = idSucursal;
	        this.direccion = direccion;
	    }
}
