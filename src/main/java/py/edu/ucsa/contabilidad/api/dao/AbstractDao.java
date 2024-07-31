package py.edu.ucsa.contabilidad.api.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public abstract class AbstractDao<PK extends Serializable,T> implements GenericDao<PK, T>{
	private final Class<T> persistentClass;
	
	protected Logger logger = null;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public AbstractDao() {
		Type genericSuperClass = getClass().getGenericSuperclass();
		ParameterizedType parametrizedType = null;
		while (parametrizedType == null && genericSuperClass != null) {
			if ((genericSuperClass instanceof ParameterizedType)) {
				parametrizedType = (ParameterizedType) genericSuperClass;
			} else {
				genericSuperClass = ((Class<?>)genericSuperClass).getGenericSuperclass();
			}
		}
		if (parametrizedType != null) {
			persistentClass = (Class<T>) parametrizedType.getActualTypeArguments()[1];
			this.logger = LoggerFactory.getLogger(persistentClass);
		} else {
			persistentClass = null;
		}
	}
	
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	public T getById(PK id) {
		return (T)this.entityManager.find(persistentClass, id);
	}
	

	public T persistir(T entity) {
		return this.entityManager.merge(entity);
	}
	
	public T actualizar(T entity) {
		return this.entityManager.merge(entity);
	}
	
	public void eliminar(T entity) {
		this.entityManager.remove(entity);
	}
	
	public void eliminar(PK id) {
		T entity = getById(id);
		this.entityManager.remove(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listar() {
		Query q = this.getEntityManager().createQuery("from " + persistentClass.getName());
		return q.getResultList();
	}
	
	
	
	
	
}
