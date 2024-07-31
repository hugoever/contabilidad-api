package py.edu.ucsa.contabilidad.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.Query;
import py.edu.ucsa.contabilidad.api.dao.AbstractDao;
import py.edu.ucsa.contabilidad.api.dao.CuentaContableDao;
import py.edu.ucsa.contabilidad.api.entities.CuentaContable;

@Repository("cuentaContableDao")
public class CuentaContableDaoImpl extends AbstractDao<Integer, CuentaContable> implements CuentaContableDao {

	@Override
	public List<CuentaContable> getCuentasByNivel(Integer nivel) {
		Query q = this.getEntityManager().createNamedQuery("CuentaContable.getCuentasByNivel");
		q.setParameter("nivel", nivel);
		List<CuentaContable> resultado = q.getResultList();
		return resultado;
	}

	@Override
	public List<CuentaContable> getCuentasAsentables() {
		Query q = this.getEntityManager().createNamedQuery("CuentaContable.getCuentasAsentables");
		List<CuentaContable> resultado = q.getResultList();
		return resultado;
	}

	@Override
	public CuentaContable getCuentaByNroCuenta(String nroCuenta) {
		Query q = this.getEntityManager().createNamedQuery("CuentaContable.getCuentaByNroCuenta");
		q.setParameter("nroCuenta", nroCuenta);
		CuentaContable resultado = (CuentaContable) q.getSingleResult();
		return resultado;
	}

}
