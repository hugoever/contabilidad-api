package py.edu.ucsa.contabilidad.api.core.services;

import py.edu.ucsa.contabilidad.api.core.entities.AsientoDet;
import py.edu.ucsa.contabilidad.api.web.dto.AsientoDetDto;

public interface AsientoDetService extends GenericService<Long, AsientoDet> {
	public AsientoDetDto listarPorId(Long id);
	public AsientoDet getByCabeceraYCuentaContable(Long idAsiento,Long idCuenta);
}
