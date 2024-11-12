package com.example.consecionaria.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.consecionaria.entity.SolicitudVehiculo;

@Repository
public interface VehiculoSolicitadoRepository extends JpaRepository<SolicitudVehiculo, Integer>{
	
	Page<SolicitudVehiculo> findBySucursalIdSucursal(int idSucursal, Pageable pageable);

}
