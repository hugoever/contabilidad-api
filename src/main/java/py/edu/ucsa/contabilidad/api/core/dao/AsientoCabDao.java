package py.edu.ucsa.contabilidad.api.core.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import py.edu.ucsa.contabilidad.api.core.entities.AsientoCab;
import py.edu.ucsa.contabilidad.api.core.entities.AsientoDet;

public interface AsientoCabDao extends GenericDao<Long, AsientoCab> {

	public AsientoCab getAsientoCabByNroAsiento(String nroAsiento);

    @Modifying
    @Query("AsientoDet.deletePorIds")
    void eliminarDetalles(List<Long> idsAEliminar);
    
    // Nuevo método para guardar un AsientoDet
   // AsientoDet saveDetalle(AsientoDet detalle);
	
}
