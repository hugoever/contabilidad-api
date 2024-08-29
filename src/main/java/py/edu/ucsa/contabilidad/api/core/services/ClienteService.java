package py.edu.ucsa.contabilidad.api.core.services;

import java.util.List;

import py.edu.ucsa.contabilidad.api.core.entities.Cliente;

public interface ClienteService extends GenericService<Long, Cliente> {
	public Cliente getByNroDocumento(String nroDocumento);
	public List<Cliente> getByNombreYApellido(String nombre, String apellido);
}
