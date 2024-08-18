package py.edu.ucsa.contabilidad.api.core.dao;

import py.edu.ucsa.contabilidad.api.core.entities.MedioPago;

public interface MedioPagoDao extends GenericDao<Long, MedioPago> {
	public MedioPago getByCodigoYCuentaContable(String codigo,Long idCuenta);
}
