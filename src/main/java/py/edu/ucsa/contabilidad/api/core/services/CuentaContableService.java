package py.edu.ucsa.contabilidad.api.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import py.edu.ucsa.contabilidad.api.entities.CuentaContable;

public interface CuentaContableService extends GenericService<Integer, CuentaContable> {
	public List<CuentaContable> getCuentasByNivel(Integer nivel);
	public List<CuentaContable> getCuentasAsentables();
	public CuentaContable getCuentaByNroCuenta(String nroCuenta);

}

