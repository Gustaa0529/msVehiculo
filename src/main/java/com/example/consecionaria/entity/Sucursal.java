package com.example.consecionaria.entity;

import java.util.List;

import com.example.consecionaria.dto.SucursalDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "sucursales")
public class Sucursal {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSucursal;

    private String direccion;

    @OneToMany(mappedBy = "sucursal")
    private List<Vehiculo> vehiculos;
    
    public Sucursal() { 
    	super(); 
    }
    public Sucursal(int idSucursal) { 
    	super(); 
    	this.idSucursal = idSucursal; 
    }
    
    public SucursalDto toDto() {
        SucursalDto dto = new SucursalDto();
        dto.setIdSucursal(this.getIdSucursal());
        dto.setDireccion(this.getDireccion());
        return dto;
    }

}
