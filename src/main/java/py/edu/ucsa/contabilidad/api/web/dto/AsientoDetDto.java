package py.edu.ucsa.contabilidad.api.web.dto;

import lombok.Getter;
import lombok.Setter;

//@Data
@Setter
@Getter
public class AsientoDetDto {
	private Long id;
    private Double montoDebe;
    private Double montoHaber;
    private Long asientoCabId; // only the ID of the AsientoCab, not the entire object
    private String asientoCabDescripcion;
    private Long cuentaContableId; // only the ID of the CuentaContable, not the entire object
    private String cuentaContableDescripcion;
}
