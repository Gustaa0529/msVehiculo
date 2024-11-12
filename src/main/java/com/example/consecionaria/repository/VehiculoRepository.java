package com.example.consecionaria.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.consecionaria.entity.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer>   {
	Page<Vehiculo> findBySucursalIdSucursal(int idSucursal, Pageable pageable);

	Vehiculo findByModeloAndSucursalIdSucursal(String modelo, int idSucursal);
}
