package py.edu.ucsa.contabilidad.api.core.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.Priority;
import jakarta.transaction.Transactional;
import py.edu.ucsa.contabilidad.api.core.dao.CobroDao;
import py.edu.ucsa.contabilidad.api.core.dao.mapper.CobroMapper;
import py.edu.ucsa.contabilidad.api.core.entities.Cobro;
import py.edu.ucsa.contabilidad.api.core.entities.CobroDetConcepto;
import py.edu.ucsa.contabilidad.api.core.entities.CobroDetMedioPago;
import py.edu.ucsa.contabilidad.api.core.services.CobroService;
import py.edu.ucsa.contabilidad.api.web.dto.CobroDto;

@Service("cobroService")
@Transactional
@Priority(0)
public class CobroServiceImpl implements CobroService {
	
	@Autowired
	private CobroDao cobroDao;

	@Autowired
	private CobroMapper cobroMapper;

	@Override
	public List<CobroDto> listar(){
		List<Cobro> cobros = cobroDao.listar();
		return cobros.stream()
				.map(cobroMapper::toDto)
				.collect(Collectors.toList());
	}
	
	@Override
	public CobroDto getById(Long id) {
		Cobro cobro = cobroDao.getById(id);
		return cobroMapper.toDto(cobro);
	}
	
	@Override
	public CobroDto persistir(CobroDto cobroDto) {
//		Cobro cobro = cobroMapper.toEntity(cobroDto);
//		Cobro persistedCobro = cobroDao.persistir(cobro);
//		return cobroMapper.toDto(persistedCobro);
		Cobro cobro = cobroMapper.toEntity(cobroDto);
		//Cobro cobroPersistido = cobroDao.persistir(cobro);
		
		for(CobroDetConcepto detalle : cobro.getDetallesConceptos()) {
			detalle.setCobro(cobro);
		}
		for(CobroDetMedioPago detalle : cobro.getDetallesMediosPagos()) {
			detalle.setCobro(cobro);
		}
		Cobro cobroPersistido = cobroDao.persistir(cobro);
		return cobroMapper.toDto(cobroPersistido);
		
	}
	
	@Override
	public CobroDto actualizar(CobroDto cobroDto) {
//		Cobro cobro = cobroMapper.toEntity(cobroDto);
//		Cobro updatedCobro = cobroDao.actualizar(cobro);
//		return cobroMapper.toDto(updatedCobro);
		Cobro cobro = cobroMapper.toEntity(cobroDto);
		//Cobro cobroPersistido = cobroDao.persistir(cobro);
		
		for(CobroDetConcepto detalle : cobro.getDetallesConceptos()) {
			detalle.setCobro(cobro);
		}
		for(CobroDetMedioPago detalle : cobro.getDetallesMediosPagos()) {
			detalle.setCobro(cobro);
		}
		Cobro cobroActualizado = cobroDao.actualizar(cobro);
		return cobroMapper.toDto(cobroActualizado);
	}
	
	@Override
	public void eliminar(CobroDto cobroDto) {
		Cobro cobro = cobroMapper.toEntity(cobroDto);
		cobro = cobroDao.getById(cobroDto.getId());
		cobroDao.eliminar(cobro);
	}

}
