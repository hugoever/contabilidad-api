package py.edu.ucsa.contabilidad.api.web.validators.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import py.edu.ucsa.contabilidad.api.core.entities.CuentaContable;
import py.edu.ucsa.contabilidad.api.web.dto.ErrorDTO;
import py.edu.ucsa.contabilidad.api.web.validators.Validador;

public class CuentaContableValidador implements Validador<CuentaContable> {

	@Override
	public List<ErrorDTO> validar(CuentaContable obj){
		List<ErrorDTO> errores = new ArrayList<>();
		
		if(Objects.isNull(obj.getAsentable())) {
			errores.add(new ErrorDTO("Se debe especificar si el la cuentas es o no asentable"));
			}
		if(Objects.isNull(obj.getCodigo())) {
			errores.add(new ErrorDTO("Se debe especificar el código de la cuenta contable"));
		}
		if(Objects.isNull(obj.getDescripcion())) {
			errores.add(new ErrorDTO("Se debe especificar la Descripción de la cuenta contable"));
		}
		if(Objects.isNull(obj.getNivel())) {
			errores.add(new ErrorDTO("El nivel de la cuenta contable es requerida"));
		}
		if(Objects.isNull(obj.getNroCuenta())) {
			errores.add(new ErrorDTO("El número de cuenta contable es requerida"));
		}
		if(Objects.isNull(obj.getTipoCuenta())) {
			errores.add(new ErrorDTO("El tipo de cuenta contable es requerida"));
		}
		
		if(errores.isEmpty()) {
			obj.setDescripcion(obj.getDescripcion().toUpperCase());
			obj.setTipoCuenta(obj.getTipoCuenta().toUpperCase());
		}
		return errores;
	}
}
