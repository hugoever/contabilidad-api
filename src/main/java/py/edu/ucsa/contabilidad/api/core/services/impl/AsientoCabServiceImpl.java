package py.edu.ucsa.contabilidad.api.core.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import py.edu.ucsa.contabilidad.api.core.dao.AsientoCabDao;
import py.edu.ucsa.contabilidad.api.core.dao.AsientoDetDao;
import py.edu.ucsa.contabilidad.api.core.dao.CuentaContableDao;
import py.edu.ucsa.contabilidad.api.core.entities.AsientoCab;
import py.edu.ucsa.contabilidad.api.core.entities.AsientoDet;
import py.edu.ucsa.contabilidad.api.core.services.AsientoCabService;
import py.edu.ucsa.contabilidad.api.web.dto.AsientoCabDto;

@Service("asientoCabService")
public class AsientoCabServiceImpl implements AsientoCabService {

	@Autowired
	private AsientoCabDao asientoCabDao;

	@Autowired
	private AsientoDetDao asientoDetDao;

//	@Autowired
//	private CuentaContableDao cuentaContableDao;

	@Override
	public List<AsientoCab> listar() {
		return asientoCabDao.listar();
	}

	@Override
	public AsientoCab getById(Long id) {
		return asientoCabDao.getById(id);

	}

	@Override
	@Transactional // (dontRollbackOn = Exception.class)
	public AsientoCab persistir(AsientoCab entity) {
//		AsientoCab cabeceraInsertada = asientoCabDao.persistir(entity);
//		//Long idCabeceraInsertada = cabeceraInsertada.getId();
//		for (AsientoDet detalle : entity.getAsientosDetalles()) {
//			detalle.setAsientoCab(cabeceraInsertada);
//			asientoDetDao.persistir(detalle);
//		}
//		 return cabeceraInsertada;

		for (AsientoDet detalle : entity.getAsientosDetalles()) {
			detalle.setAsientoCab(entity);

		}
//		
//		return asientoCabDao.persistir(entity);
		return asientoCabDao.persistir(entity); // Persistir la cabecera, los detalles se gestionan automáticamente

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public AsientoCab actualizar(AsientoCab entity) {
		 AsientoCab asientoExistente = asientoCabDao.getById(entity.getId());

	        // Obtener los detalles existentes y nuevos
	        List<AsientoDet> detallesExistentes = asientoExistente != null ?
	                new ArrayList<>(asientoExistente.getAsientosDetalles()) :
	                new ArrayList<>();
	        List<AsientoDet> nuevosDetalles = new ArrayList<>(entity.getAsientosDetalles());

	        // Identificar los índices de los detalles a eliminar
	        List<Integer> indicesAEliminar = new ArrayList<>();
	        for (int i = 0; i < detallesExistentes.size(); i++) {
	            if (!nuevosDetalles.contains(detallesExistentes.get(i))) {
	                indicesAEliminar.add(i);
	            }
	        }

	        // Eliminar los detalles de la base de datos
	        // (Asumiendo que tienes un método en el repositorio para eliminar por ID)
	        Iterator<AsientoDet> iterator = detallesExistentes.iterator();
	        while (iterator.hasNext()) {
	            AsientoDet detalle = iterator.next();
	            if (!nuevosDetalles.contains(detalle)) {
	                asientoDetDao.eliminar(detalle.getId());
	                iterator.remove(); // Elimina el elemento del iterador y de la lista
	            }
	        }

	        // Agregar los nuevos detalles
	        detallesExistentes.addAll(nuevosDetalles);

	        // Asignar los detalles actualizados al asiento
	        entity.setAsientosDetalles(detallesExistentes);

	        // Persistir los cambios
	        return asientoCabDao.actualizar(entity);

	}

	@Override
	public List<AsientoCabDto> getAll() {
		List<AsientoCab> asientosCab = asientoCabDao.listar();
		List<AsientoCabDto> asientosCabDto = new ArrayList<>();
		for (AsientoCab asientoCab : asientosCab) {
			AsientoCabDto asientoCabDto = new AsientoCabDto(asientoCab.getId(), asientoCab.getDescripcion(),
					asientoCab.getEstado(), asientoCab.getFechaAsiento(), asientoCab.getFechaRegistro(),
					asientoCab.getNroAsiento());
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

	@Override
	@Transactional
	public void eliminar(AsientoCab entity) {
		asientoCabDao.eliminar(entity);
		
	}

}
