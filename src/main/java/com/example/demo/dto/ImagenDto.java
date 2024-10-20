package com.example.demo.dto;

import java.util.Base64;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ImagenDto {
	
    private String nombre;
    private String contenido; 
    private int idVehiculo; 

    public ImagenDto() {
        super();
    }

    public ImagenDto(String nombre, byte[] contenido, int idVehiculo) {
        super();
        this.nombre = nombre;
        this.contenido = Base64.getEncoder().encodeToString(contenido);
        this.idVehiculo = idVehiculo;
    }

}
