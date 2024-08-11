package py.edu.ucsa.contabilidad.api.core.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.FetchType;
import jakarta.transaction.Transactional;
import py.edu.ucsa.contabilidad.api.core.dao.AsientoDetDao;
import py.edu.ucsa.contabilidad.api.core.entities.AsientoCab;
import py.edu.ucsa.contabilidad.api.core.entities.AsientoDet;
import py.edu.ucsa.contabilidad.api.core.entities.CuentaContable;
import py.edu.ucsa.contabilidad.api.core.services.AsientoDetService;
import py.edu.ucsa.contabilidad.api.web.dto.AsientoDetDto;

@Service("asientoDetService")
@Transactional
public class AsientoDetServiceImpl implements AsientoDetService {

	@Autowired
	private AsientoDetDao asientoDetDao;
	
	@Override
	public List<AsientoDet> listar() {
		return asientoDetDao.listar();
	}

	@Override
	public Optional<AsientoDet> getById(Long id, FetchType eager) {

	    return Optional.ofNullable(asientoDetDao.getById(id));
	  
	}

	@Override
	public AsientoDet persistir(AsientoDet entity) {
		
		return asientoDetDao.persistir(entity);
	}

	@Override
	public AsientoDet actualizar(AsientoDet entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eliminar(AsientoDet entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public AsientoDetDto listarPorId(Long id) {
		AsientoDet asientoDet = asientoDetDao.getById(id);
		AsientoDetDto asientoDetDto = new AsientoDetDto();
		asientoDetDto.setId(asientoDet.getId());
		asientoDetDto.setMontoDebe(asientoDet.getMontoDebe());
		asientoDetDto.setMontoHaber(asientoDet.getMontoHaber());
		asientoDetDto.setAsientoCabId(asientoDet.getAsientoCab().getId());
		asientoDetDto.setCuentaContableId(asientoDet.getCuentaContable().getId());
		return asientoDetDto;
	}

}
