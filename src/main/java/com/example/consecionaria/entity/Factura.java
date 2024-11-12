package com.example.consecionaria.entity;

import com.example.consecionaria.dto.FacturaEstadoEnum;

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
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFactura;

    private String nombre;
    private String correo;
    private String metodoPago;
    private int telefono;
    
    @Enumerated(EnumType.STRING)
    private FacturaEstadoEnum estado;

    // Relación con el vehículo
    @ManyToOne
    @JoinColumn(name = "idVehiculo")
    private Vehiculo vehiculo;
    
    public Factura() {
		super();
	}
    
    public Factura(String nombre, String correo, String metodoPago, int telefono, FacturaEstadoEnum estado,
			Vehiculo vehiculo) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.metodoPago = metodoPago;
		this.telefono = telefono;
		this.estado = estado;
		this.vehiculo = vehiculo;
	}

	public Factura(int idFactura, String nombre, String correo, String metodoPago, int telefono, FacturaEstadoEnum estado,
			Vehiculo vehiculo) {
		super();
		this.idFactura = idFactura;
		this.nombre = nombre;
		this.correo = correo;
		this.metodoPago = metodoPago;
		this.telefono = telefono;
		this.estado = estado;
		this.vehiculo = vehiculo;
	}
}
