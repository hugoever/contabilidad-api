package py.edu.ucsa.contabilidad.api.core.dao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import py.edu.ucsa.contabilidad.api.core.entities.Cliente;
import py.edu.ucsa.contabilidad.api.web.dto.ClienteDto;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

	ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
	
	ClienteDto toDto(Cliente cliente);
	Cliente toEntity(ClienteDto clienteDto);
}
