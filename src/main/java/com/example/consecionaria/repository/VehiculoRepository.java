package com.example.consecionaria.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.consecionaria.entity.Vehiculo;

@Repository
public interface VehiculoRepository extends CrudRepository<Vehiculo, Integer>, PagingAndSortingRepository<Vehiculo, Integer>    {
	Page<Vehiculo> findBySucursalIdSucursal(int idSucursal, Pageable pageable);
}
