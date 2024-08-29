package py.edu.ucsa.contabilidad.api.web.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CuentaContableDto {

	private Long id;
	private Boolean asentable;
	private String codigo;
	private String descripcion;
	private Integer nivel;
	private String nroCuenta;
	private String tipoCuenta; // A:ACTIVO, P:PASIVO, D:DEUDORA, H:ACREEDORA
}
