package py.edu.ucsa.contabilidad.api.web.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CobroDetConceptoDto {
	private Long id;
	private Double monto;
	private ConceptoCobroDto conceptoCobro;

}
