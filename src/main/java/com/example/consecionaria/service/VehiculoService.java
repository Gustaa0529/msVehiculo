package com.example.consecionaria.service;

import org.springframework.data.domain.Page;

import com.example.consecionaria.dto.VehiculoDto;

public interface VehiculoService {

	/*public List<Vehiculo> listar() throws Exception;*/
	
	public Page<VehiculoDto> listarConPaginadoPorSucursal(Integer size, String sort, Integer numPage, int idSucursal) throws Exception;
	public VehiculoDto agregarVehiculo(VehiculoDto vehiculoDto) throws Exception;
	public VehiculoDto descontarStock(int id, int cantidad) throws Exception;
	public VehiculoDto actualizarPrecio(int id, int nuevoPrecio) throws Exception;

}
