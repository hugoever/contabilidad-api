package py.edu.ucsa.contabilidad.api.core.dao;

import py.edu.ucsa.contabilidad.api.core.entities.AsientoCab;

public interface AsientoCabDao extends GenericDao<Long, AsientoCab> {

	public AsientoCab getAsientoCabByNroAsiento(String nroAsiento);
	
}
