package py.edu.ucsa.contabilidad.api.core.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.FetchType;
import jakarta.transaction.Transactional;
import py.edu.ucsa.contabilidad.api.core.dao.AsientoCabDao;
import py.edu.ucsa.contabilidad.api.core.entities.AsientoCab;
import py.edu.ucsa.contabilidad.api.core.services.AsientoCabService;
import py.edu.ucsa.contabilidad.api.web.dto.AsientoCabDto;

@Service("asientoCabService")
@Transactional
public class AsientoCabServiceImpl implements AsientoCabService {

	@Autowired
	private AsientoCabDao asientoCabDao;
	
	@Override
	public List<AsientoCab> listar() {
		return asientoCabDao.listar();
	}

	@Override
	public Optional<AsientoCab> getById(Long id, FetchType eager) {
	  if (eager == FetchType.EAGER) {
	    return Optional.ofNullable(asientoCabDao.getById(id));
	  } else {
	    // Handle non-eager case if needed (default behavior)
	    return Optional.ofNullable(asientoCabDao.getById(id));
	  }
	}

	@Override
	public AsientoCab persistir(AsientoCab entity) {
		return asientoCabDao.persistir(entity);
	}

	@Override
	public AsientoCab actualizar(AsientoCab entity) {
		return asientoCabDao.persistir(entity);
	}

	@Override
	public void eliminar(AsientoCab entity) {
		asientoCabDao.eliminar(entity);

	}

	@Override
	public List<AsientoCabDto> getAll(){
		List<AsientoCab> asientosCab = asientoCabDao.listar();
		List<AsientoCabDto> asientosCabDto = new ArrayList<>();
		for (AsientoCab asientoCab : asientosCab) {
			AsientoCabDto asientoCabDto = new AsientoCabDto(
					asientoCab.getId(),
					asientoCab.getDescripcion(),
					asientoCab.getEstado(),
					asientoCab.getFechaAsiento(),
					asientoCab.getFechaRegistro(),
					asientoCab.getNroAsiento()					
					);
			asientosCabDto.add(asientoCabDto);
		}
		return asientosCabDto;
	}

	@Override
	public boolean isExisteAsientoCab(String nroAsiento) {
			return Objects.nonNull(getAsientoCabByNroAsiento(nroAsiento));

	}

	private Object getAsientoCabByNroAsiento(String nroAsiento) {
		return asientoCabDao.getAsientoCabByNroAsiento(nroAsiento);
	}
	

}
