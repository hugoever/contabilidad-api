package py.edu.ucsa.contabilidad.api.core.dao.impl;

import org.springframework.stereotype.Repository;

import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;
import py.edu.ucsa.contabilidad.api.core.dao.AbstractDao;
import py.edu.ucsa.contabilidad.api.core.dao.AsientoDetDao;
import py.edu.ucsa.contabilidad.api.core.entities.AsientoDet;

@Repository("asientoDetDao")
public class AsientoDetDaoImpl extends AbstractDao<Long, AsientoDet> implements AsientoDetDao {

	@Override
	public AsientoDet getByCabeceraYCuentaContable(Long idAsiento, Long idCuenta) {
		try {
			Query q = this.getEntityManager().createNamedQuery("AsientoDet.getByCabeceraYCuentaContable");
			q.setParameter("idAsiento",idAsiento);
			q.setParameter("idCuenta", idCuenta);
			return (AsientoDet) q.getSingleResult();			
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			return null;
		}
	}

}
