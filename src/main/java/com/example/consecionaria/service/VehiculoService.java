package com.example.consecionaria.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.consecionaria.dto.VehiculoDto;
import com.example.consecionaria.entity.Vehiculo;

public interface VehiculoService {

	/*public List<Vehiculo> listar() throws Exception;*/
	
	public Page<VehiculoDto> listarConPaginado(Integer size, String sort, Integer numPage, int idSucursal) throws Exception;

}
