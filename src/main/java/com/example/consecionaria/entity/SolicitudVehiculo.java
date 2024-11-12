package com.example.consecionaria.entity;

import com.example.consecionaria.dto.EstadoEnum;
import com.example.consecionaria.dto.SolicitudVehiculoDto;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "solicitudes_vehiculo")
public class SolicitudVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSolicitud; 

    @ManyToOne
    @JoinColumn(name = "idVehiculo") 
    private Vehiculo vehiculo;
    
    @ManyToOne
    @JoinColumn(name = "idSucursalDestino") 
    private Sucursal sucursal;
    
    @Enumerated(EnumType.STRING)
    private EstadoEnum estado; 
    
    public SolicitudVehiculoDto toDto() {
        SolicitudVehiculoDto dto = new SolicitudVehiculoDto();
        dto.setIdSolicitud(this.getIdSolicitud());
        dto.setVehiculoDto(this.getVehiculo().toDto());
        dto.setSucursal(this.getSucursal().toDto());
        dto.setEstado(this.getEstado());
        return dto;
    }
}

