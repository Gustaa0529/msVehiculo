package com.example.consecionaria.service;

import org.springframework.data.domain.Page;

import com.example.consecionaria.dto.FacturaEstadoEnum;
import com.example.consecionaria.dto.FacturaDto;

public interface FacturaService {

	public Page<FacturaDto> listarConPaginadoPorSucursal(Integer size, String sort, Integer numPage, int idSucursal) throws Exception;
	public FacturaDto obtenerFacturaPorId(int idFactura) throws Exception;
	public void eliminarFactura(int idFactura);
	public FacturaDto agregarFactura(FacturaDto facturaDto) throws Exception;
	public FacturaDto actualizarEstadoFactura(int idFactura, FacturaEstadoEnum nuevoEstado) throws Exception;
	
}
