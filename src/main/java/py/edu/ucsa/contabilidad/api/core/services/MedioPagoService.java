package py.edu.ucsa.contabilidad.api.core.services;

import py.edu.ucsa.contabilidad.api.core.entities.MedioPago;

public interface MedioPagoService extends GenericService<Long, MedioPago> {
	public MedioPago getByCodigoYCuentaContable(String codigo,Long idCuenta);
}
