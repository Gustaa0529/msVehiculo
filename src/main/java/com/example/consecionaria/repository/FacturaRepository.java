package com.example.consecionaria.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.consecionaria.entity.Factura;


public interface FacturaRepository extends JpaRepository<Factura, Integer> {
	
	public Page<Factura> findByVehiculoSucursalIdSucursal(int idSucursal, Pageable pageable);

}
