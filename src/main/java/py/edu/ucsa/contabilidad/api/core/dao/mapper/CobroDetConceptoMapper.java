package py.edu.ucsa.contabilidad.api.core.dao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import py.edu.ucsa.contabilidad.api.core.entities.CobroDetConcepto;
import py.edu.ucsa.contabilidad.api.web.dto.CobroDetConceptoDto;

@Mapper(componentModel = "spring",uses = ConceptoCobroMapper.class)
public interface CobroDetConceptoMapper {
	CobroDetConceptoMapper INSTANCE = Mappers.getMapper(CobroDetConceptoMapper.class);

	CobroDetConceptoDto toDto(CobroDetConcepto cobroDetConcepto);
	CobroDetConcepto toEntity(CobroDetConceptoDto cobroDetConceptoDto);
}
