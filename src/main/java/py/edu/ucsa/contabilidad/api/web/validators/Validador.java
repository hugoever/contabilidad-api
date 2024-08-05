package py.edu.ucsa.contabilidad.api.web.validators;

import java.util.List;

import py.edu.ucsa.contabilidad.api.web.dto.ErrorDTO;

//Interface que será implementada por las clases validadoras,
//que se encargarán de realizar algún tipo de validación
public interface Validador<T> {
//	Método que será utilizado para realizar la validación 
//	sobre el objeto que recibe como parámetro
//	Parámetro: objeto a ser validado obj
//	Return : lista de errores que puede surgir a partir de la validación
	List<ErrorDTO> validar(T obj);

}
