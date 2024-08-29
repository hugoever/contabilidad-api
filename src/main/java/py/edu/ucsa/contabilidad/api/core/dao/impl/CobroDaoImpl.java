package py.edu.ucsa.contabilidad.api.core.dao.impl;

import org.springframework.stereotype.Repository;

import py.edu.ucsa.contabilidad.api.core.dao.AbstractDao;
import py.edu.ucsa.contabilidad.api.core.dao.CobroDao;
import py.edu.ucsa.contabilidad.api.core.entities.Cobro;

@Repository("cobroDao")
public class CobroDaoImpl extends AbstractDao<Long, Cobro> implements CobroDao {	

}
