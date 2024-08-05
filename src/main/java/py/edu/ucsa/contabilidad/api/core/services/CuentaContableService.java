package py.edu.ucsa.contabilidad.api.core.services;

import java.util.List;

import py.edu.ucsa.contabilidad.api.core.entities.CuentaContable;

public interface CuentaContableService extends GenericService<Integer, CuentaContable> {
	public List<CuentaContable> getCuentasByNivel(Integer nivel);
	public List<CuentaContable> getCuentasAsentables();
	public CuentaContable getCuentaByNroCuenta(String nroCuenta);
	public boolean isExisteCuentaContable(String nroCuenta);
	public CuentaContable getCuentaByCodigo(String codigo);
	public List<CuentaContable> getCuentasByTipo(String tipoCuenta);
	public List<CuentaContable> getCuentasHijas(Integer id);
}

