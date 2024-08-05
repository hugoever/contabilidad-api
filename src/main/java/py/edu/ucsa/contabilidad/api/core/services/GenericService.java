package py.edu.ucsa.contabilidad.api.core.services;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.FetchType;

public interface GenericService<PK,T>{

	List<T> listar();
	Optional<T> getById(PK id, FetchType eager);
	T persistir(T entity);
	T actualizar(T entity);
	void eliminar(T entity);
}

