package py.edu.ucsa.contabilidad.api.web.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteDto {

	private Long id;
	private String apellido;
	private String nombre;
	private String nroDocumento;
	
}
