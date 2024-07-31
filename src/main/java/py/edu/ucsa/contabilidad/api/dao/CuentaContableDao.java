package py.edu.ucsa.contabilidad.api.dao;

import java.util.List;

import py.edu.ucsa.contabilidad.api.entities.CuentaContable;

public interface CuentaContableDao extends GenericDao<Integer, CuentaContable> {
	public List<CuentaContable> getCuentasByNivel(Integer nivel);
	public List<CuentaContable> getCuentasAsentables();
	public CuentaContable getCuentaByNroCuenta(String nroCuenta);

}
