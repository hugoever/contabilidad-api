package py.edu.ucsa.contabilidad.api.core.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medios_pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "MedioPago.getByCodigoYCuentaContable", query = "SELECT m FROM MedioPago m WHERE m.codigo = :codigo AND m.cuentaContable.id = :idCuenta")
public class MedioPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "descripcion")
	private String descripcion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cuenta_contable", nullable = false)
	private CuentaContable cuentaContable;

}
