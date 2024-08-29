package py.edu.ucsa.contabilidad.api.core.dao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import py.edu.ucsa.contabilidad.api.core.entities.CobroDetMedioPago;
import py.edu.ucsa.contabilidad.api.web.dto.CobroDetMedioPagoDto;

@Mapper(componentModel = "spring",uses = MedioPagoMapper.class)
public interface CobroDetMedioPagoMapper {
	CobroDetMedioPagoMapper INSTANCE = Mappers.getMapper(CobroDetMedioPagoMapper.class);

	CobroDetMedioPagoDto toDto(CobroDetMedioPago cobroDetMedioPago);
	CobroDetMedioPago toEntity(CobroDetMedioPagoDto cobroDetMedioPagoDto);
}
