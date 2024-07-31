package py.edu.ucsa.contabilidad.api.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
		if(cuentaContable.getNroCuenta())
	}
	
	
}
