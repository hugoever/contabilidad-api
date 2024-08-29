package py.edu.ucsa.contabilidad.api.core.services;

import py.edu.ucsa.contabilidad.api.core.entities.ConceptoCobro;

public interface ConceptoCobroService extends GenericService<Long, ConceptoCobro> {
	public ConceptoCobro getByCodigoYCuentaContable(String codigo,Long idCuenta);
}
