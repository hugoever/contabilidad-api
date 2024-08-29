package py.edu.ucsa.contabilidad.api.web.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConceptoCobroDto {
	private Long id;
	private String codigo;
	private String descripcion;
	private CuentaContableDto cuentaContable;
}
