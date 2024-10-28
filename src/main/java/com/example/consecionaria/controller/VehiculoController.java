package com.example.consecionaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.consecionaria.dto.PaginadoDto;
import com.example.consecionaria.dto.VehiculoDto;
import com.example.consecionaria.entity.Vehiculo;
import com.example.consecionaria.service.VehiculoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

	
	@Autowired
	private VehiculoService vehiculoService;
	
	/*@GetMapping(value = "/listar", produces = {MediaType.APPLICATION_JSON_VALUE }  )
	public ResponseEntity<List<Vehiculo>> listar() throws Exception{
		return new ResponseEntity<List<Vehiculo>>(vehiculoService.listar(), HttpStatus.OK);
	}*/
	
	@GetMapping(value = "/listar/paginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaginadoDto<VehiculoDto>> listarConPaginado(
	        @RequestParam Integer size,
	        @RequestParam String sort,
	        @RequestParam Integer numPage,
	        @RequestParam int idSucursal) throws Exception {
	    Page<VehiculoDto> pageResult = vehiculoService.listarConPaginado(size, sort, numPage, idSucursal);
	    PaginadoDto<VehiculoDto> response = new PaginadoDto<>(
	            pageResult.getContent(),
	            pageResult.getTotalElements(),
	            pageResult.getTotalPages(),
	            pageResult.getSize(),
	            pageResult.getNumber()
	    );
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}


}
