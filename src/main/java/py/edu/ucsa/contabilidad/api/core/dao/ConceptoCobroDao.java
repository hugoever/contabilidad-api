package py.edu.ucsa.contabilidad.api.core.dao;

import py.edu.ucsa.contabilidad.api.core.entities.ConceptoCobro;

public interface ConceptoCobroDao extends GenericDao<Long, ConceptoCobro> {
	public ConceptoCobro getByCodigoYCuentaContable(String codigo,Long idCuenta);
}
