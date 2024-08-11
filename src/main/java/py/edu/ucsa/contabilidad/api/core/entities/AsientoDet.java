package py.edu.ucsa.contabilidad.api.core.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "asientos_det")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
	 //@JsonIgnore
	 @JsonBackReference
	 private AsientoCab asientoCab;
	 
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "id_cuenta_contable", nullable = false)
	 private CuentaContable cuentaContable;
	 
	 
}