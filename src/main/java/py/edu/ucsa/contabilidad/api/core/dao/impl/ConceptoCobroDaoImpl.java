package py.edu.ucsa.contabilidad.api.core.dao.impl;

import org.springframework.stereotype.Repository;

import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;
import py.edu.ucsa.contabilidad.api.core.dao.AbstractDao;
import py.edu.ucsa.contabilidad.api.core.dao.ConceptoCobroDao;
import py.edu.ucsa.contabilidad.api.core.entities.ConceptoCobro;
import py.edu.ucsa.contabilidad.common.exceptions.CustomMultipleResultsException;
import py.edu.ucsa.contabilidad.common.exceptions.CustomNotFoundException;

@Repository("conceptoCobroDao")
public class ConceptoCobroDaoImpl extends AbstractDao<Long, ConceptoCobro> implements ConceptoCobroDao {

	@Override
	public ConceptoCobro getByCodigoYCuentaContable(String codigo, Long idCuenta) {
		try {
			Query q = this.getEntityManager().createNamedQuery("ConceptoCobro.getByCodigoYCuentaContable");
			q.setParameter("codigo",codigo);
			q.setParameter("idCuenta",idCuenta);
			return (ConceptoCobro) q.getSingleResult();			
		} catch (NoResultException e) {
			throw new CustomNotFoundException("No se encontró el medio de pago con el código: "+codigo+" y el id de cuenta contable: "+idCuenta);
		} catch (NonUniqueResultException e) {
			throw new CustomMultipleResultsException("Se encontraron varios medios de pago con el código: "+codigo+" y el id de cuenta contable: "+idCuenta);
		}
	}

}
