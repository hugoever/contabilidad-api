package py.edu.ucsa.contabilidad.api.web.dto;

import lombok.Getter;
import lombok.Setter;
import py.edu.ucsa.contabilidad.api.core.entities.CuentaContable;

@Setter
@Getter
public class MedioPagoDto {
	private Long id;
	private String codigo;
	private String descripcion;
	private CuentaContableDto cuentaContable;
}
