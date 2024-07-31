package py.edu.ucsa.contabilidad.api.entities;

import java.util.Objects;

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
@Table(name="cuentas_contables")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "CuentaContable.getCuentasByNivel",
			query = "SELECT cc FROM CuentaContable cc WHERE cc.nivel = :nivel")
@NamedQuery(name = "CuentaContable.getCuentasAsentables",
			query = "SELECT cc FROM CuentaContable cc WHERE cc.asentable = true")
@NamedQuery(name = "CuentaContable.getCuentaByNroCuenta",
			query = "SELECT cc FROM CuentaContable cc WHERE cc.nroCuenta = :nroCuenta")
public class CuentaContable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="asentable")
	private Boolean asentable;
	
	@Column(name="codigo")
	private String codigo;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="nivel")
	private Integer nivel;
	
	@Column(name="numero_cuenta")
	private String nroCuenta;
	
	@Column(name="tipo_cuenta", nullable = false)
	private String tipoCuenta; //A:ACTIVO, P:PASIVO, D:DEUDORA, H:ACREEDORA
	
	@ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "id_cuenta_padre")
	  private CuentaContable cuentaPadre;

	 // Overriding equals and hashcode for entity comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaContable that = (CuentaContable) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
