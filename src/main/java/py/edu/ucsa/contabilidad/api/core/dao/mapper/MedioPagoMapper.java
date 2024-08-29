package py.edu.ucsa.contabilidad.api.core.dao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import py.edu.ucsa.contabilidad.api.core.entities.MedioPago;
import py.edu.ucsa.contabilidad.api.web.dto.MedioPagoDto;

@Mapper(componentModel = "spring",uses = CuentaContableMapper.class)
public interface MedioPagoMapper {
	MedioPagoMapper INSTANCE = Mappers.getMapper(MedioPagoMapper.class);
	
	MedioPagoDto toDto(MedioPago medioPago);
	MedioPago toEntity(MedioPagoDto medioPagoDto);
}
