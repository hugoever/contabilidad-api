package py.edu.ucsa.contabilidad.api.core.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "asientos_det")
//@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "AsientoDet.getByCabeceraYCuentaContable", query = "SELECT d FROM AsientoDet d WHERE d.asientoCab.id = :idAsiento AND d.cuentaContable.id = :idCuenta")
@NamedQuery(name = "AsientoDet.deletePorIds", query = "DELETE FROM AsientoDet ad WHERE ad.id IN :ids")
public class AsientoDet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "monto_debe")
	private Double montoDebe;

	@Column(name = "monto_haber")
	private Double montoHaber;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_asiento", nullable = false)
	@JsonBackReference
	private AsientoCab asientoCab;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cuenta_contable", nullable = false)
	private CuentaContable cuentaContable;

}
