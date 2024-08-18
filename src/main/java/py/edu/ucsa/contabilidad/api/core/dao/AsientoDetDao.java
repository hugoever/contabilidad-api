package py.edu.ucsa.contabilidad.api.core.dao;

import py.edu.ucsa.contabilidad.api.core.entities.AsientoDet;

public interface AsientoDetDao extends GenericDao<Long, AsientoDet> {
	public AsientoDet getByCabeceraYCuentaContable(Long idAsiento,Long idCuenta);
}
