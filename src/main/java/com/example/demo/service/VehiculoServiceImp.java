package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ImagenDto;
import com.example.demo.dto.SucursalDto;
import com.example.demo.dto.VehiculoDto;
import com.example.demo.entity.Vehiculo;
import com.example.demo.repository.VehiculoRepository;

@Service
public class VehiculoServiceImp implements VehiculoService {

	@Autowired
	private VehiculoRepository vehiculoRepository;

	private VehiculoDto convertToDto(Vehiculo vehiculo) {
	    VehiculoDto dto = new VehiculoDto();
	    dto.setIdVehiculo(vehiculo.getIdVehiculo());
	    dto.setModelo(vehiculo.getModelo());
	    dto.setStock(vehiculo.getStock());
	    dto.setPrecio(vehiculo.getPrecio());

	    // Convertir la entidad Sucursal a SucursalDto y asignarla al DTO de Vehiculo
	    if (vehiculo.getSucursal() != null) {
	        SucursalDto sucursalDto = new SucursalDto(vehiculo.getSucursal().getIdSucursal(), vehiculo.getSucursal().getDireccion());
	        dto.setSucursal(sucursalDto);
	    }

	    // Convertir la lista de imágenes a DTOs de imágenes
	    if (vehiculo.getImagenes() != null) {
	        List<ImagenDto> imagenDtos = vehiculo.getImagenes().stream()
	            .map(imagen -> new ImagenDto(imagen.getNombre(), imagen.getContenido(), vehiculo.getIdVehiculo()))
	            .collect(Collectors.toList());
	        dto.setImagenes(imagenDtos);
	    }
	    return dto;
	}

	
    public Page<VehiculoDto> listarConPaginadoV2(Integer size, String sort, Integer numPage) throws Exception {
        if (size <= 0) {
            throw new IllegalArgumentException("El tamaño de la página debe ser mayor que cero.");
        }
        if (numPage < 0) {
            throw new IllegalArgumentException("El número de página no puede ser negativo.");
        }
        if (sort == null || sort.trim().isEmpty()) {
            sort = "idVehiculo"; // Cambia "idVehiculo" al campo que consideres adecuado
        }

        Pageable paggin = PageRequest.of(numPage, size, Sort.by(sort));
        Page<Vehiculo> pageResult = vehiculoRepository.findAll(paggin);

        // Convertir a DTO
        return pageResult.map(this::convertToDto);
    }

	/*@Override
	public List<Vehiculo> listar() throws Exception {
		return (List<Vehiculo>) vehiculoRepository.findAll();
		List<VehiculoDto> vehiculo = new ArrayList<VehiculoDto>(); 
		vehiculoRepository.findAll().forEach(x ->{
			VehiculoDto unVehiculoDto = new VehiculoDto();
			unVehiculoDto.setModelo(x.getModelo());
			unVehiculoDto.setColor(x.getColor());
			unVehiculoDto.setStock(x.getStock());
			unVehiculoDto.setPrecio(x.getPrecio());
			unVehiculoDto.setIdsucursal(x.getIdVehiculo());
			vehiculo.add(unVehiculoDto);
		});
		return vehiculo;
	}*/
	
}
