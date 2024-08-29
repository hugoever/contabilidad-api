package py.edu.ucsa.contabilidad.api.web.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CobroDto {
	private Long id;
	private LocalDateTime fechaCobro;
	private Double montoTotal;
	private ClienteDto cliente;
	private List<CobroDetConceptoDto> detallesConceptos;
	private List<CobroDetMedioPagoDto> detallesMediosPagos;
	

}
