package py.edu.ucsa.contabilidad.api.web.controllers;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import py.edu.ucsa.contabilidad.api.core.services.CuentaContableService;
import py.edu.ucsa.contabilidad.api.entities.CuentaContable;
import py.edu.ucsa.contabilidad.api.web.dto.ErrorDTO;

@RestController
@RequestMapping("cuentas-contables")
public class CuentaContableController {
	
	private static final Logger logger = LoggerFactory.getLogger(CuentaContable.class);
	
	@Autowired
	@Qualifier("cuentaContableService")
	private CuentaContableService cuentaContableService;
	
	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok(cuentaContableService.listar());		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id){
		return ResponseEntity.ok(cuentaContableService.getById(id));
	}

	@PostMapping
	public ResponseEntity<?> crearCuentaContable(@RequestBody CuentaContable cuentaContable, UriComponentsBuilder ucBuilder) {
		logger.info("Creando la cuenta contable : {}", cuentaContable);
		if(Objects.nonNull(cuentaContableService.getCuentaByNroCuenta(cuentaContable.getNroCuenta()))) {
			logger.error("Inserción fallida, ya existe la cuenta contable número: {}"+cuentaContable.getNroCuenta());
			return new ResponseEntity<ErrorDTO>(new ErrorDTO ("Ya existe la cuenta contable con el número: "+cuentaContable.getNroCuenta()), HttpStatus.CONFLICT);
		}
			CuentaContable insertado = cuentaContableService.persistir(cuentaContable);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/cuentas-contables/{id}").buildAndExpand(insertado.getId()).toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
			
		}
	}
	
	

