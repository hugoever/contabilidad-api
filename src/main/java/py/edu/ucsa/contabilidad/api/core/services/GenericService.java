package py.edu.ucsa.contabilidad.api.core.services;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.FetchType;

public interface GenericService<PK,T>{

	List<T> listar();
	T getById(PK id);
	T persistir(T entity);
	T actualizar(T entity);
	void eliminar(T entity);
}

