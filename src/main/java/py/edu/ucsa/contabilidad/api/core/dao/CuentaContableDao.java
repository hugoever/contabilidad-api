package py.edu.ucsa.contabilidad.api.core.dao;

import java.util.List;

import py.edu.ucsa.contabilidad.api.core.entities.CuentaContable;

public interface CuentaContableDao extends GenericDao<Integer, CuentaContable> {
	public List<CuentaContable> getCuentasByNivel(Integer nivel);
	public List<CuentaContable> getCuentasAsentables();
	public CuentaContable getCuentaByNroCuenta(String nroCuenta);
	public CuentaContable getCuentaByCodigo(String codigo);
	public List<CuentaContable> getCuentasByTipo(String tipoCuenta);
	public List<CuentaContable> getCuentasHijas(Integer id);
	

}
