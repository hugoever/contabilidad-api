package py.edu.ucsa.contabilidad.api.core.dao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import py.edu.ucsa.contabilidad.api.core.entities.ConceptoCobro;
import py.edu.ucsa.contabilidad.api.web.dto.ConceptoCobroDto;

@Mapper(componentModel = "spring",uses = CuentaContableMapper.class)
public interface ConceptoCobroMapper {
    ConceptoCobroMapper INSTANCE = Mappers.getMapper(ConceptoCobroMapper.class);

    ConceptoCobroDto toDto(ConceptoCobro conceptoCobro);
    ConceptoCobro toEntity(ConceptoCobroDto conceptoCobroDto);
}
