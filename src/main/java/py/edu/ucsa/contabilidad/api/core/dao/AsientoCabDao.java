package py.edu.ucsa.contabilidad.api.core.dao;

import java.util.Optional;

import py.edu.ucsa.contabilidad.api.core.entities.AsientoCab;

public interface AsientoCabDao extends GenericDao<Long, AsientoCab> {

	public AsientoCab getAsientoCabByNroAsiento(String nroAsiento);
	
}
