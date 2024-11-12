package com.example.consecionaria.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.consecionaria.dto.FacturaEstadoEnum;
import com.example.consecionaria.dto.FacturaDto;
import com.example.consecionaria.dto.SucursalDto;
import com.example.consecionaria.dto.VehiculoDto;
import com.example.consecionaria.entity.Factura;
import com.example.consecionaria.entity.Vehiculo;
import com.example.consecionaria.repository.FacturaRepository;
import com.example.consecionaria.repository.VehiculoRepository;

@Service
public class FacturaServiceImp implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    private FacturaDto convertToDto(Factura factura) {
        FacturaDto dto = new FacturaDto();
        dto.setIdFactura(factura.getIdFactura());
        dto.setNombre(factura.getNombre());
        dto.setCorreo(factura.getCorreo());
        dto.setMetodoPago(factura.getMetodoPago());
        dto.setTelefono(factura.getTelefono());
        dto.setEstado(factura.getEstado());

        if (factura.getVehiculo() != null) {
            VehiculoDto vehiculoDto = new VehiculoDto();
            vehiculoDto.setIdVehiculo(factura.getVehiculo().getIdVehiculo());
            vehiculoDto.setModelo(factura.getVehiculo().getModelo());
            vehiculoDto.setStock(factura.getVehiculo().getStock());
            vehiculoDto.setPrecio(factura.getVehiculo().getPrecio());

            if (factura.getVehiculo().getSucursal() != null) {
                SucursalDto sucursalDto = new SucursalDto();
                sucursalDto.setIdSucursal(factura.getVehiculo().getSucursal().getIdSucursal());
                sucursalDto.setDireccion(factura.getVehiculo().getSucursal().getDireccion());
                vehiculoDto.setSucursal(sucursalDto);
            }
            dto.setVehiculoDto(vehiculoDto);
        }

        return dto;
    }

    public Page<FacturaDto> listarConPaginadoPorSucursal(Integer size, String sort, Integer numPage, int idSucursal) {
        if (size <= 0) {
            throw new IllegalArgumentException("El tamaño de la página debe ser mayor que cero.");
        }
        if (numPage < 0) {
            throw new IllegalArgumentException("El número de página no puede ser negativo.");
        }
        if (sort == null || sort.trim().isEmpty()) {
            sort = "id";
        }

        Pageable pageable = PageRequest.of(numPage, size, Sort.by(sort));
        Page<Factura> pageResult = facturaRepository.findByVehiculoSucursalIdSucursal(idSucursal, pageable);

        return pageResult.map(this::convertToDto);
    }

    public FacturaDto agregarFactura(FacturaDto facturaDto) throws Exception {
        FacturaEstadoEnum estadoFactura = facturaDto.getEstado();
        VehiculoDto vehiculoDto = facturaDto.getVehiculoDto();
        int idVehiculo = vehiculoDto.getIdVehiculo();

        Optional<Vehiculo> optionalVehiculo = vehiculoRepository.findById(idVehiculo);

        if (!optionalVehiculo.isPresent()) {
            throw new RuntimeException("Vehículo no encontrado");
        }

        Vehiculo vehiculo = optionalVehiculo.get();

        if (vehiculo.getStock() <= 0) {
            throw new RuntimeException("Stock insuficiente");
        }

        vehiculo.setStock(vehiculo.getStock() - 1);
        vehiculoRepository.save(vehiculo);

        Factura factura = new Factura();
        factura.setNombre(facturaDto.getNombre());
        factura.setCorreo(facturaDto.getCorreo());
        factura.setMetodoPago(facturaDto.getMetodoPago());
        factura.setTelefono(facturaDto.getTelefono());
        factura.setEstado(estadoFactura);
        factura.setVehiculo(vehiculo);

        Factura facturaGuardada = facturaRepository.save(factura);
        return convertToDto(facturaGuardada);
    }

    public FacturaDto obtenerFacturaPorId(int idFactura) throws Exception {
        Optional<Factura> factura = facturaRepository.findById(idFactura);
        if (factura.isPresent()) {
            return convertToDto(factura.get());
        } else {
            throw new RuntimeException("Factura no encontrada");
        }
    }

    public void eliminarFactura(int idFactura){
        Optional<Factura> optionalFactura = facturaRepository.findById(idFactura);

        if (!optionalFactura.isPresent()) {
            throw new RuntimeException("Factura no encontrada");
        }

        Factura factura = optionalFactura.get();
        Vehiculo vehiculo = factura.getVehiculo();

        if (vehiculo != null) {
            vehiculo.setStock(vehiculo.getStock() + 1);
            vehiculoRepository.save(vehiculo);
        }

        facturaRepository.deleteById(idFactura);
    }

    public FacturaDto actualizarEstadoFactura(int id, FacturaEstadoEnum nuevoEstado) throws Exception {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));

        factura.setEstado(nuevoEstado);
        facturaRepository.save(factura);

        return convertToDto(factura);
    }
}
