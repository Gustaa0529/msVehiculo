package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.dto.VehiculoDto;
import com.example.demo.entity.Vehiculo;

public interface VehiculoService {

	/*public List<Vehiculo> listar() throws Exception;*/
	
	public Page<VehiculoDto> listarConPaginadoV2( Integer size, String sort, Integer numPage ) throws Exception;

}
