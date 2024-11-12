package com.example.consecionaria.controller;

import com.example.consecionaria.dto.FacturaEstadoEnum;
import com.example.consecionaria.dto.FacturaDto;
import com.example.consecionaria.dto.PaginadoDto;
import com.example.consecionaria.service.FacturaService;
import com.example.consecionaria.entity.Factura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")  // Permitir acceso CORS para el front-end
@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    // Método para crear una nueva factura
    @PostMapping("/crearFactura")
    public ResponseEntity<FacturaDto> crearFactura(@RequestBody FacturaDto facturaDto) throws Exception {
        FacturaDto nuevaFactura = facturaService.agregarFactura(facturaDto); 
        return new ResponseEntity<>(nuevaFactura, HttpStatus.CREATED); 
    }

    // Método para obtener todas las facturas con paginación y filtrado por idSucursal
    @GetMapping(value = "/paginado", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaginadoDto<FacturaDto>> listarFacturasPaginadas(
            @RequestParam Integer size,          // Tamaño de la página
            @RequestParam String sort,          // Criterio de ordenación (por ejemplo, "idFactura")
            @RequestParam Integer numPage,      // Número de página
            @RequestParam int idSucursal)       // Filtro por idSucursal
            throws Exception {
        
        Page<FacturaDto> pageResult = facturaService.listarConPaginadoPorSucursal(size, sort, numPage, idSucursal);
        
        // Crear respuesta con la paginación
        PaginadoDto<FacturaDto> response = new PaginadoDto<>(
                pageResult.getContent(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages(),
                pageResult.getSize(),
                pageResult.getNumber()
        );
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Método para obtener una factura por su id
    @GetMapping("/{id}")
    public ResponseEntity<FacturaDto> obtenerFacturaPorId(@PathVariable int idFactura) throws Exception {
        FacturaDto facturaDto = facturaService.obtenerFacturaPorId(idFactura);
        return new ResponseEntity<>(facturaDto, HttpStatus.OK);
    }

    @PutMapping("/actualizarEstado/{idFactura}")
    public ResponseEntity<FacturaDto> actualizarEstadoFactura(@PathVariable int idFactura, @RequestBody FacturaEstadoEnum nuevoEstado) throws Exception {
        FacturaDto facturaDto = facturaService.actualizarEstadoFactura(idFactura, nuevoEstado);
        return new ResponseEntity<>(facturaDto, HttpStatus.OK);
    }

    // Método para eliminar una factura por su id
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarFactura(@PathVariable int id) throws Exception {
        facturaService.eliminarFactura(id);
        return new ResponseEntity<>("Factura eliminada correctamente", HttpStatus.OK);
    }
}
