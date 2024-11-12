package com.example.consecionaria.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.consecionaria.dto.EstadoEnum;
import com.example.consecionaria.dto.ImagenDto;
import com.example.consecionaria.dto.SolicitudVehiculoDto;
import com.example.consecionaria.dto.SucursalDto;
import com.example.consecionaria.dto.VehiculoDto;
import com.example.consecionaria.entity.Imagen;
import com.example.consecionaria.entity.SolicitudVehiculo;
import com.example.consecionaria.entity.Sucursal;
import com.example.consecionaria.entity.Vehiculo;
import com.example.consecionaria.repository.VehiculoRepository;
import com.example.consecionaria.repository.VehiculoSolicitadoRepository;

@Service
public class VehiculoServiceImp implements VehiculoService{

	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	@Autowired
	private VehiculoSolicitadoRepository vehiculoSolicitadoRepository;

	private VehiculoDto convertToDto(Vehiculo vehiculo) {
		VehiculoDto dto = new VehiculoDto();
		dto.setIdVehiculo(vehiculo.getIdVehiculo());
		dto.setModelo(vehiculo.getModelo());
		dto.setStock(vehiculo.getStock());
		dto.setPrecio(vehiculo.getPrecio());

		if (vehiculo.getSucursal() != null) {
			SucursalDto sucursalDto = new SucursalDto(
					vehiculo.getSucursal().getIdSucursal(),
					vehiculo.getSucursal().getDireccion()
					);
			dto.setSucursal(sucursalDto);
		}

		if (vehiculo.getImagenes() != null) {
			List<ImagenDto> imagenDtos = vehiculo.getImagenes().stream()
					.map(imagen -> new ImagenDto(imagen.getNombre(), imagen.getRuta(), vehiculo.getIdVehiculo()))
					.collect(Collectors.toList());
			dto.setImagenes(imagenDtos);
		}
		return dto;
	}
	
	private SolicitudVehiculoDto convertToSolicitudVehiculoDto(SolicitudVehiculo solicitudVehiculo) {
        SolicitudVehiculoDto dto = new SolicitudVehiculoDto();
        dto.setIdSolicitud(solicitudVehiculo.getIdSolicitud());
        dto.setVehiculoDto(convertToDto(solicitudVehiculo.getVehiculo()));
        SucursalDto sucursalDto = new SucursalDto(
            solicitudVehiculo.getSucursal().getIdSucursal(),
            solicitudVehiculo.getSucursal().getDireccion()
        );
        dto.setSucursal(sucursalDto);
        dto.setEstado(solicitudVehiculo.getEstado());
        return dto;
    }

	public VehiculoDto actualizarPrecio(int id, int nuevoPrecio) throws Exception{ 
		Optional<Vehiculo> optionalVehiculo = vehiculoRepository.findById(id); 
		if (optionalVehiculo.isPresent()) { 
			Vehiculo vehiculo = optionalVehiculo.get(); 
			vehiculo.setPrecio(nuevoPrecio); 
			Vehiculo vehiculoActualizado = vehiculoRepository.save(vehiculo); 
			return convertToDto(vehiculoActualizado); 
			} else { 
				throw new RuntimeException("Vehículo no encontrado"); 
			} 
		}
	
	public VehiculoDto descontarStock(int id, int cantidad) throws Exception{ 
		Optional<Vehiculo> optionalVehiculo = vehiculoRepository.findById(id); 
		if (optionalVehiculo.isPresent()) 
		{ 
			Vehiculo vehiculo = optionalVehiculo.get();
			int nuevoStock = vehiculo.getStock() - cantidad; 
			if (nuevoStock < 0) { 
				throw new RuntimeException("Stock insuficiente"); 
				} 
			vehiculo.setStock(nuevoStock); 
			Vehiculo vehiculoActualizado = vehiculoRepository.save(vehiculo); 
			return convertToDto(vehiculoActualizado); 
			} else {
				throw new RuntimeException("Vehículo no encontrado"); } 
		}

	public VehiculoDto agregarVehiculo(VehiculoDto vehiculoDto) throws Exception{
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setModelo(vehiculoDto.getModelo());
		vehiculo.setStock(vehiculoDto.getStock());
		vehiculo.setPrecio(vehiculoDto.getPrecio());
		vehiculo.setSucursal(new Sucursal(vehiculoDto.getSucursal().getIdSucursal()));
		vehiculo.setImagenes(vehiculoDto.getImagenes().stream().map(imagenDto -> {
			Imagen imagen = new Imagen();
			imagen.setNombre(imagenDto.getNombre());
			imagen.setRuta(imagenDto.getRuta());
			imagen.setVehiculo(vehiculo); 
			return imagen;
		}).collect(Collectors.toList()));

		Vehiculo nuevoVehiculo = vehiculoRepository.save(vehiculo);
		vehiculoDto.setIdVehiculo(nuevoVehiculo.getIdVehiculo());
		return vehiculoDto;
	}

	public Page<VehiculoDto> listarConPaginadoPorSucursal(Integer size, String sort, Integer numPage, int idSucursal) throws Exception {
		if (size <= 0) {
			throw new IllegalArgumentException("El tamaño de la página debe ser mayor que cero.");
		}
		if (numPage < 0) {
			throw new IllegalArgumentException("El número de página no puede ser negativo.");
		}
		if (sort == null || sort.trim().isEmpty()) {
			sort = "idVehiculo";
		}

		Pageable paggin = PageRequest.of(numPage, size, Sort.by(sort));
		Page<Vehiculo> pageResult = vehiculoRepository.findBySucursalIdSucursal(idSucursal, paggin);

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
	

	public SolicitudVehiculo guardarSolicitud(SolicitudVehiculoDto solicitudVehiculoDto) {
        SolicitudVehiculo solicitudVehiculo = new SolicitudVehiculo();
        solicitudVehiculo.setVehiculo(solicitudVehiculoDto.getVehiculoDto().toEntity());
        solicitudVehiculo.setSucursal(solicitudVehiculoDto.getSucursal().toEntity());
        solicitudVehiculo.setEstado(solicitudVehiculoDto.getEstado());

        return vehiculoSolicitadoRepository.save(solicitudVehiculo);
    }

    public Page<SolicitudVehiculoDto> listarSolicitudesPaginado(Integer size, String sort, Integer numPage, int idSucursal) throws Exception {
        if (size <= 0) {
            throw new IllegalArgumentException("El tamaño de la página debe ser mayor que cero.");
        }
        if (numPage < 0) {
            throw new IllegalArgumentException("El número de página no puede ser negativo.");
        }
        if (sort == null || sort.trim().isEmpty()) {
            sort = "idSolicitud";
        }

        Pageable pagin = PageRequest.of(numPage, size, Sort.by(sort));
        Page<SolicitudVehiculo> pageResult = vehiculoSolicitadoRepository.findBySucursalIdSucursal(idSucursal, pagin);

        return pageResult.map(this::convertToSolicitudVehiculoDto);
    }
    
    @Override
    public void eliminarSolicitud(int id) throws Exception {
    	vehiculoSolicitadoRepository.deleteById(id);
    }
    
    @Override
    public SolicitudVehiculoDto actualizarEstadoSolicitud(int id, EstadoEnum nuevoEstado) throws Exception {
        SolicitudVehiculo solicitud = vehiculoSolicitadoRepository.findById(id)
                .orElseThrow(() -> new Exception("Solicitud no encontrada"));

        solicitud.setEstado(nuevoEstado);

        if (nuevoEstado == EstadoEnum.VEHICULOENVIADO) {
            Vehiculo vehiculo = solicitud.getVehiculo();
            vehiculo.setStock(vehiculo.getStock() - 1);
            vehiculoRepository.save(vehiculo);

            Vehiculo vehiculoEnOtraSucursal = vehiculoRepository.findByModeloAndSucursalIdSucursal(
                    vehiculo.getModelo(), solicitud.getSucursal().getIdSucursal());

            if (vehiculoEnOtraSucursal != null) {
                vehiculoEnOtraSucursal.setStock(vehiculoEnOtraSucursal.getStock() + 1);
            } else {
                Vehiculo nuevoVehiculo = new Vehiculo();
                nuevoVehiculo.setModelo(vehiculo.getModelo());
                nuevoVehiculo.setStock(1);
                nuevoVehiculo.setSucursal(solicitud.getSucursal());
                vehiculoRepository.save(nuevoVehiculo);
            }
        }

        vehiculoSolicitadoRepository.save(solicitud);
        return solicitud.toDto();
    }




}
