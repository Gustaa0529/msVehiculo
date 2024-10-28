package com.example.consecionaria.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "solicitudes_vehiculo")
public class SolicitudVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSolicitud; 

    private int idVehiculo;
    private int idSucursalDestino;
    private int idEmpleado;
    private String estado; 
    
    public SolicitudVehiculo() { }

    public SolicitudVehiculo(int idVehiculo, int idSucursalDestino, int idEmpleado, String estado) {
        this.idVehiculo = idVehiculo;
        this.idSucursalDestino = idSucursalDestino;
        this.idEmpleado = idEmpleado;
        this.estado = estado;
    }
}

