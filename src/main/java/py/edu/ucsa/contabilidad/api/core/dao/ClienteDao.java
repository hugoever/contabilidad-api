package py.edu.ucsa.contabilidad.api.core.dao;

import java.util.List;

import py.edu.ucsa.contabilidad.api.core.entities.Cliente;

public interface ClienteDao extends GenericDao<Long, Cliente> {
	public Cliente getByNroDocumento(String nroDocumento);
	public List<Cliente> getByNombreYApellido(String nombre, String apellido);
}
