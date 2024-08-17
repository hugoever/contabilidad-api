package py.edu.ucsa.contabilidad.api.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import py.edu.ucsa.contabilidad.api.core.dao.AbstractDao;
import py.edu.ucsa.contabilidad.api.core.dao.AsientoDetDao;
import py.edu.ucsa.contabilidad.api.core.entities.AsientoDet;
import py.edu.ucsa.contabilidad.api.core.services.AsientoDetService;
import py.edu.ucsa.contabilidad.api.web.dto.AsientoDetDto;
import py.edu.ucsa.contabilidad.common.exceptions.CustomMultipleResultsException;
import py.edu.ucsa.contabilidad.common.exceptions.CustomNotFoundException;

@Service("asientoDetService")
@Transactional
public class AsientoDetServiceImpl extends AbstractDao<Long, AsientoDet> implements AsientoDetService {

	@Autowired
	private AsientoDetDao asientoDetDao;

	@Override
	public List<AsientoDet> listar() {
		return asientoDetDao.listar();
	}

	@Override
	public AsientoDet getById(Long id) {

		return asientoDetDao.getById(id);

	}

	@Override
	public AsientoDet persistir(AsientoDet entity) {
		return asientoDetDao.persistir(entity);
	}

	@Override
	public AsientoDet actualizar(AsientoDet entity) {
		return asientoDetDao.persistir(entity);
	}

	@Override
	public void eliminar(AsientoDet entity) {
		asientoDetDao.eliminar(entity);

	}

	@Override
	public AsientoDetDto listarPorId(Long id) {

		AsientoDet asientoDet;

		asientoDet = asientoDetDao.getById(id);
		if (asientoDet == null) {
			// Handle the case where the asientoDet is not found
			throw new EntityNotFoundException("AsientoDet no encontrado con id " + id);
			// Alternatively, return null or handle it in another way
		}
		AsientoDetDto asientoDetDto = new AsientoDetDto();
		asientoDetDto.setId(asientoDet.getId());
		asientoDetDto.setMontoDebe(asientoDet.getMontoDebe());
		asientoDetDto.setMontoHaber(asientoDet.getMontoHaber());
		asientoDetDto.setAsientoCabId(asientoDet.getAsientoCab().getId());
		asientoDetDto.setAsientoCabDescripcion(asientoDet.getAsientoCab().getDescripcion());
		asientoDetDto.setCuentaContableId(asientoDet.getCuentaContable().getId());
		asientoDetDto.setCuentaContableDescripcion(asientoDet.getCuentaContable().getDescripcion());
		return asientoDetDto;

	}

	@Override
	public AsientoDet getByCabeceraYCuentaContable(Long idAsiento, Long idCuenta) {
		 try {
		        return asientoDetDao.getByCabeceraYCuentaContable(idAsiento, idCuenta);
		    } catch (CustomNotFoundException e) {
		        // Log or rethrow the exception, or handle it with specific business logic
		        throw e;
		    } catch (CustomMultipleResultsException e) {
		        // Log or rethrow the exception, or handle it with specific business logic
		        throw e;
		    }
	}

}
