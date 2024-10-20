package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class SolicitudVehiculoDto {
    private int idVehiculo;
    private int idSucursalDestino;
    private String estado; 

    public SolicitudVehiculoDto() {
        super();
    }

    public SolicitudVehiculoDto(int idVehiculo, int idSucursalDestino, String estado) {
        this.idVehiculo = idVehiculo;
        this.idSucursalDestino = idSucursalDestino;
        this.estado = estado;
    }

}
