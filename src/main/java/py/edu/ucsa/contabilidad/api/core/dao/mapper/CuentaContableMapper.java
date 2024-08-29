package py.edu.ucsa.contabilidad.api.core.dao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import py.edu.ucsa.contabilidad.api.core.entities.CuentaContable;
import py.edu.ucsa.contabilidad.api.web.dto.CuentaContableDto;

@Mapper(componentModel = "spring")
public interface CuentaContableMapper {
	CuentaContableMapper INSTANCE = Mappers.getMapper(CuentaContableMapper.class);

    CuentaContableDto toDto(CuentaContable cuentaContable);
    CuentaContable toEntity(CuentaContableDto cuentaContableDto);
}
