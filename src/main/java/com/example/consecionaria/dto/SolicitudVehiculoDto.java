package com.example.consecionaria.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class SolicitudVehiculoDto {
	
	private int idSolicitud; 
    private VehiculoDto vehiculoDto;
    private SucursalDto Sucursal;
    private EstadoEnum estado; 
  
    
}
