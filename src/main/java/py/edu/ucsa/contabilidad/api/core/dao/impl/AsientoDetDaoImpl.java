package py.edu.ucsa.contabilidad.api.core.dao.impl;

import org.springframework.stereotype.Repository;

import py.edu.ucsa.contabilidad.api.core.dao.AbstractDao;
import py.edu.ucsa.contabilidad.api.core.dao.AsientoDetDao;
import py.edu.ucsa.contabilidad.api.core.entities.AsientoDet;

@Repository("asientoDetDao")
public class AsientoDetDaoImpl extends AbstractDao<Long, AsientoDet> implements AsientoDetDao {

}
