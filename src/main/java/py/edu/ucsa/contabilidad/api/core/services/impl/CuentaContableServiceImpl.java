package py.edu.ucsa.contabilidad.api.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import py.edu.ucsa.contabilidad.api.core.services.CuentaContableService;
import py.edu.ucsa.contabilidad.api.dao.CuentaContableDao;
import py.edu.ucsa.contabilidad.api.entities.CuentaContable;

@Service("cuentaContableService")
@Transactional
public class CuentaContableServiceImpl implements CuentaContableService {
	
	@Autowired
	private CuentaContableDao cuentaContableDao;

	@Override
	public List<CuentaContable> listar() {
		return cuentaContableDao.listar();
	}

	@Override
	public CuentaContable getById(Integer id) {
		return cuentaContableDao.getById(id);
	}

	@Override
	public CuentaContable persistir(CuentaContable entity) {
		return cuentaContableDao.persistir(entity);
	}

	@Override
	public CuentaContable actualizar(CuentaContable entity) {
		return cuentaContableDao.persistir(entity);
	}

	@Override
	public void eliminar(CuentaContable entity) {
		cuentaContableDao.eliminar(entity);

	}

	@Override
	public List<CuentaContable> getCuentasByNivel(Integer nivel) {
		return cuentaContableDao.getCuentasByNivel(nivel);
	}

	@Override
	public List<CuentaContable> getCuentasAsentables() {
		return cuentaContableDao.getCuentasAsentables();
	}

	@Override
	public CuentaContable getCuentaByNroCuenta(String nroCuenta) {
		return cuentaContableDao.getCuentaByNroCuenta(nroCuenta);
	}

}

