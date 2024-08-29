package py.edu.ucsa.contabilidad.api.core.dao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import py.edu.ucsa.contabilidad.api.core.entities.Cobro;
import py.edu.ucsa.contabilidad.api.web.dto.CobroDto;

@Mapper(componentModel = "spring", uses = { ClienteMapper.class, CobroDetConceptoMapper.class, CobroDetMedioPagoMapper.class })
public interface CobroMapper {
	CobroMapper INSTANCE = Mappers.getMapper(CobroMapper.class);
	
	@Mapping(source = "detallesConceptos", target = "detallesConceptos")
	@Mapping(source = "detallesMediosPagos", target = "detallesMediosPagos")
	CobroDto toDto(Cobro cobro);
	
	@Mapping(source = "detallesConceptos", target = "detallesConceptos")
	@Mapping(source = "detallesMediosPagos", target = "detallesMediosPagos")
	Cobro toEntity(CobroDto cobroDto);

}
