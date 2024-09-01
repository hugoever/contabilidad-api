package py.edu.ucsa.contabilidad.api.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import py.edu.ucsa.contabilidad.api.core.dao.AbstractDao;
import py.edu.ucsa.contabilidad.api.core.dao.AsientoCabDao;
import py.edu.ucsa.contabilidad.api.core.entities.AsientoCab;

@Repository("asientoCabDao")
public class AsientoCabDaoImpl extends AbstractDao<Long, AsientoCab> implements AsientoCabDao {

	@Override
	public AsientoCab getAsientoCabByNroAsiento(String nroAsiento) {
		try {
			Query q = this.getEntityManager().createNamedQuery("AsientoCab.getAsientoCabByNroAsiento");
			q.setParameter("nroAsiento", nroAsiento);
            return (AsientoCab) q.getSingleResult();
        } catch (NoResultException e) {
            logger.info("No se encontró un asiento con el número: {}", nroAsiento);
            return null; // O lanzar una excepción personalizada
            //throw new NonUniqueResultException("No hay resultados para el número de cuenta");
        } catch (NonUniqueResultException e) {
            logger.error("Se encontraron múltiples asiento con el número: {}", nroAsiento);
            throw new NonUniqueResultException("Múltiples resultados para el número de cuenta");
        }
//		Query q = this.getEntityManager().createNamedQuery("AsientoCab.getAsientoCabByNroAsiento");
//		q.setParameter("nroAsiento", nroAsiento);
//		try {
//			AsientoCab resultado = (AsientoCab) q.getSingleResult();
//			return Optional.of(resultado);
//		}catch (NoResultException e) {
//			return Optional.empty();
//		}
	}

	@Override
    @Transactional
    public void eliminarDetalles(List<Long> ids) {
        // No es necesario implementar lógica adicional aquí
        // Spring Data JPA se encargará de ejecutar la named query
    }
	
//	 @Override
//	    public AsientoDet saveDetalle(AsientoDet detalle) {
//	        return entityManager.merge(detalle);
//	    }

}
