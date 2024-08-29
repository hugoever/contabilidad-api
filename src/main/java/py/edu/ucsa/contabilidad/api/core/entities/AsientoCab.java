package py.edu.ucsa.contabilidad.api.core.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import py.edu.ucsa.contabilidad.api.web.dto.ErrorDto;
import py.edu.ucsa.contabilidad.api.web.validators.Validable;
import py.edu.ucsa.contabilidad.api.web.validators.Validador;

@Entity
@Table(name = "asientos_cab")
//@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "AsientoCab.getAsientoCabByNroAsiento", query = "SELECT a FROM AsientoCab a WHERE a.nroAsiento = :nroAsiento")
public class AsientoCab implements Serializable, Validable<AsientoCab>{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "descripcion", length = 200)
    private String descripcion;
    
    @Column(name = "estado", length = 3)
    private String estado;
    
    @Column(name = "fecha_asiento")
    private Timestamp fechaAsiento;
    
    @Column(name = "fecha_registro")
    private Timestamp fechaRegistro;
    
    @Column(name = "nro_asiento", length = 255)
    private String nroAsiento;
    
//    @OneToMany(mappedBy = "asientoCab", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonBackReference
//    private List<AsientoDet> detalles;
    
  //PARA INSERTAR DETALLES	
	@OneToMany(mappedBy = "asientoCab", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<AsientoDet> asientosDetalles = new ArrayList<>();
//
//    @OneToMany(mappedBy = "cobro", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<CobroDetMedioPago> detallesMedioPago = new ArrayList<>();
//	 public void addAsientoDet(AsientoDet detalle) {
//	        asientosDetalles.add(detalle);
//	        detalle.setAsientoCab(this);
//	    }
//
//	    public void removeAsientoDet(AsientoDet detalle) {
//	        asientosDetalles.remove(detalle);
//	        detalle.setAsientoCab(null);
//	    }
//	
	@Transient
    @JsonIgnore
	private List<Validador<AsientoCab>> validadores;
	public List<Validador<AsientoCab>> getValidadores() {
		if(Objects.isNull(validadores)) {
			validadores = new ArrayList<>();
		}
		return validadores;
	}
//	@Override
//	public boolean equals(Object o) {
//	    if (this == o) return true;
//	    if (o == null || getClass() != o.getClass()) return false;
//	    AsientoCab that = (AsientoCab) o;
//	    return Objects.equals(id, that.id);
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(id);
//	}
		
	@Override
	public List<ErrorDto> validar() {
		List<ErrorDto> errores = new ArrayList<>();
		this.getValidadores().forEach(v -> errores.addAll(v.validar(this)));
		return errores;
	}

	@Override
	public void agregarValidador(Validador<AsientoCab> v) {
		this.getValidadores().add(v);
		
	}
    
}
