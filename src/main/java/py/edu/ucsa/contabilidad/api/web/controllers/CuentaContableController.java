package py.edu.ucsa.contabilidad.api.web.controllers;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.persistence.FetchType;
import py.edu.ucsa.contabilidad.api.core.entities.CuentaContable;
import py.edu.ucsa.contabilidad.api.core.services.CuentaContableService;
import py.edu.ucsa.contabilidad.api.web.dto.ErrorDTO;
import py.edu.ucsa.contabilidad.api.web.validators.impl.CuentaContableValidador;

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
	public ResponseEntity<?> getById(@PathVariable("id") Integer id, @RequestParam(required = false) boolean includePadre){
		//return ResponseEntity.ok(cuentaContableService.getById(id), FetchMode.SELECT);
		Optional<CuentaContable> cuentaContable;
	    if (includePadre) {
	        cuentaContable = cuentaContableService.getById(id, FetchType.EAGER);
	    } else {
	        cuentaContable = cuentaContableService.getById(id, null);
	    }
	    return ResponseEntity.ok(cuentaContable);
	}

	@PostMapping
	public ResponseEntity<?> crearCuentaContable(@RequestBody CuentaContable cuentaContable, UriComponentsBuilder ucBuilder) {
		logger.info("Creando la cuenta contable : {}", cuentaContable);
		//if(Objects.nonNull(cuentaContableService.getCuentaByNroCuenta(cuentaContable.getNroCuenta()))) {
		if(cuentaContableService.isExisteCuentaContable(cuentaContable.getNroCuenta())) {
			logger.error("Inserción fallida, ya existe la cuenta contable número: "+cuentaContable.getNroCuenta());
			return new ResponseEntity<ErrorDTO>(new ErrorDTO ("Ya existe la cuenta contable con el número: "+cuentaContable.getNroCuenta()), HttpStatus.CONFLICT);
		}
		CuentaContableValidador v1 = new CuentaContableValidador();
		cuentaContable.agregarValidador(v1);		
		List<ErrorDTO> errores = cuentaContable.validar();
		if(errores.isEmpty()) {
			CuentaContable insertado = cuentaContableService.persistir(cuentaContable);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/cuentas-contables/{id}").buildAndExpand(insertado.getId()).toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<List<ErrorDTO>>(errores, HttpStatus.CREATED);
		}
			
		}
	
	@PutMapping("{id}")
	public ResponseEntity<?> actualizarCuentaContable(@PathVariable("id") Integer id, @RequestBody CuentaContable cuenta){
		logger.info("Actualizando opción con el id: {}", id);
		Optional<CuentaContable> cuentaBD = cuentaContableService.getById(id, null);
		if(Objects.isNull(cuentaBD)) {
			logger.error("Actualización fallida, no existe la cuenta contable: {}", id);
			return new ResponseEntity<ErrorDTO>(
					new ErrorDTO("Actualización fallida. No existe la cuenta contable con el id " + id), HttpStatus.NOT_FOUND);
		}
		cuentaContableService.actualizar(cuenta);
		return new ResponseEntity<Optional<CuentaContable>>(cuentaBD,HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> eliminarCuentaContable(@PathVariable("id") Integer id){
		logger.info("Eliminar cuenta contable con el id: {}", id);
		Optional<CuentaContable> cuentaBD = cuentaContableService.getById(id, null);
		if(Objects.isNull(cuentaBD)) {
			logger.error("Eliminación fallida, no existe la cuenta contable: {}", id);
			return new ResponseEntity<ErrorDTO>(
					new ErrorDTO("Eliminación fallida. No existe la cuenta contable con el id " + id), HttpStatus.NOT_FOUND);
		}
		String cuentaEliminada = cuentaBD.get().getDescripcion();
		cuentaContableService.eliminar(cuentaBD.get());
		return new ResponseEntity<ErrorDTO>(new ErrorDTO("Cuenta Contable "+ cuentaEliminada + " eliminada satisfactoriamente."),HttpStatus.OK);
	}
	
	@GetMapping("/por-nivel/{nivel}")
	public ResponseEntity<?> getCuentasByNivel(@PathVariable("nivel") Integer nivel){
		List<CuentaContable> cuentasBD = cuentaContableService.getCuentasByNivel(nivel);
		if(cuentasBD == null || cuentasBD.isEmpty()) {
			logger.error("No existen cuentas con el nivel: {}", nivel);
			return new ResponseEntity<ErrorDTO>(
					new ErrorDTO("No existen cuentas con el nivel: " + nivel), HttpStatus.NOT_FOUND);
		}
		cuentaContableService.getCuentasByNivel(nivel);
		return ResponseEntity.ok(cuentasBD);
	}
	
	@GetMapping("/asentables")
	public ResponseEntity<?> getCuentasAsentables(){
		List<CuentaContable> cuentasAsentables = cuentaContableService.getCuentasAsentables();
		return ResponseEntity.ok(cuentasAsentables);
	}
	
	@GetMapping("/por-numero/{nroCuenta}")
	public ResponseEntity<?> getCuentaByNroCuenta(@PathVariable("nroCuenta") String nroCuenta){
		CuentaContable cuentasBD = cuentaContableService.getCuentaByNroCuenta(nroCuenta);
		if(Objects.isNull(cuentasBD)) {
			logger.error("No se encuentra la cuenta número: {}", nroCuenta);
			return new ResponseEntity<ErrorDTO>(
					new ErrorDTO("No se encuentra la cuenta número: " + nroCuenta), HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(cuentaContableService.getCuentaByNroCuenta(nroCuenta));
		
	}
	
	@GetMapping("/por-codigo/{codigo}")
	public ResponseEntity<?> getCuentaByCodigo(@PathVariable("codigo") String codigo){
		CuentaContable cuentaBD = cuentaContableService.getCuentaByCodigo(codigo);
		if(Objects.isNull(cuentaBD)) {
			logger.error("No se encuentra la cuenta con el código: {}", codigo);
			return new ResponseEntity<ErrorDTO>(
					new ErrorDTO("No se encuentra la cuenta con el código: {}"+codigo),HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(cuentaContableService.getCuentaByCodigo(codigo));
	}
	
	@GetMapping("/por-tipo/{tipoCuenta}")
	public ResponseEntity<?> getCuentasByTipo(@PathVariable("tipoCuenta") String tipoCuenta){
		tipoCuenta = Character.toUpperCase(tipoCuenta.charAt(0)) + tipoCuenta.substring(1);//primera letra mayúscula y el resto minúscula
		List<CuentaContable> cuentasBD = cuentaContableService.getCuentasByTipo(tipoCuenta);
		if(cuentasBD == null || cuentasBD.isEmpty()) {
			logger.error("No existen cuentas con el tipo: {}", tipoCuenta);
			return new ResponseEntity<ErrorDTO>(
					new ErrorDTO("No existen cuentas con el tipo: " + tipoCuenta), HttpStatus.NOT_FOUND);
		}
		cuentaContableService.getCuentasByTipo(tipoCuenta);
		return ResponseEntity.ok(cuentasBD);
	}
	
	@GetMapping("/hijas/{id}")
	public ResponseEntity<?> getCuentasHijas(@PathVariable("id") Integer id){
		List<CuentaContable> cuentasBD = cuentaContableService.getCuentasHijas(id);
		Optional<CuentaContable> c = cuentaContableService.getById(id, null);
		if(Objects.isNull(c)) {
			logger.error("No se encuentra la cuenta con el id: {}", id);
			return new ResponseEntity<ErrorDTO>(
					new ErrorDTO("No se encuentra la cuenta con el id: {}"+id),HttpStatus.NOT_FOUND);
		}
		if(cuentasBD == null || cuentasBD.isEmpty()) {
			logger.error("No existen cuentas hijas con el padre: {}", c.get().getDescripcion() );
			return new ResponseEntity<ErrorDTO>(
					new ErrorDTO("No existen cuentas hijas con el padre: " + c.get().getDescripcion()), HttpStatus.NOT_FOUND);
		}
		cuentaContableService.getCuentasHijas(id);
		return ResponseEntity.ok(cuentasBD);
	}
	
	}
	
	

