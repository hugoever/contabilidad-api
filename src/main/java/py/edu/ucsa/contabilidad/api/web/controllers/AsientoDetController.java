package py.edu.ucsa.contabilidad.api.web.controllers;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.persistence.NoResultException;
import py.edu.ucsa.contabilidad.api.core.entities.AsientoDet;
import py.edu.ucsa.contabilidad.api.core.services.AsientoDetService;
import py.edu.ucsa.contabilidad.api.web.dto.AsientoDetDto;
import py.edu.ucsa.contabilidad.api.web.dto.ErrorDto;

@RestController
@RequestMapping("asientos-detalles")
public class AsientoDetController {
	private static final Logger logger = LoggerFactory.getLogger(AsientoDet.class);
	
	@Autowired
	@Qualifier("asientoDetService")
	private AsientoDetService asientoDetService;
	
	private AsientoDetDto asientoDetDto;
	
	
	@GetMapping("/listar")
	public ResponseEntity<?> listar(){
		return ResponseEntity.ok(asientoDetService.listar());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> listarPorId(@PathVariable("id") Long id){
		//return ResponseEntity.ok(cuentaContableService.getById(id), FetchMode.SELECT);
		AsientoDetDto asientoDetDto;
	    
	    	asientoDetDto = asientoDetService.listarPorId(id);
	    
	    return ResponseEntity.ok(asientoDetDto);
	}	

	@GetMapping("/porcabeceraycuenta/{idAsiento}/{idCuenta}") public ResponseEntity<?> getByCabeceraYCuentaContable(@PathVariable("idAsiento") Long idAsiento, @PathVariable("idCuenta") Long idCuenta){
		try {
			return ResponseEntity.ok(asientoDetService.getByCabeceraYCuentaContable(idAsiento, idCuenta));
		} catch (NoResultException e) {
			return new ResponseEntity<>(new ErrorDto("No se encontró el detalle"), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> crearAsientoDet(@RequestBody AsientoDet asientoDet, UriComponentsBuilder ucBuilder) {
		logger.info("Creando el detalle  : {}", asientoDet.getId());
		AsientoDet insertado = asientoDetService.persistir(asientoDet);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/asientos-detalles/{id}").buildAndExpand(insertado.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> actualizarAsientoDet(@PathVariable("id") Long id, @RequestBody AsientoDet asientoDet){
		logger.info("Actualizando el detalle con el id: {}",id);
		Optional<AsientoDet> asientoBD = asientoDetService.getById(id,null);
		if(Objects.isNull(asientoBD)) {
			logger.info("Actualización fallida, detalle con el id {} no existe",id);
			return new ResponseEntity<ErrorDto>(
					new ErrorDto("Actualización fallida, no existe detalle con el id: {}"+id),HttpStatus.NOT_FOUND);
		}
		asientoDetService.persistir(asientoDet);
		asientoDetDto = asientoDetService.listarPorId(id);
		return new ResponseEntity<AsientoDetDto>(asientoDetDto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> eliminarAsientoDet(@PathVariable("id") Long id){
		logger.info("Eliminando detalle del asiento con el id: {}", id);
		Optional<AsientoDet> detalleBD = asientoDetService.getById(id, null);
		if(Objects.isNull(detalleBD)) {
			logger.error("Eliminación fallida, no existe el detalle del asiento: {}", id);
			return new ResponseEntity<ErrorDto>(
					new ErrorDto("Eliminación fallida, no existe el detalle del asiento " + id), HttpStatus.NOT_FOUND);
		}
		Long detalleEliminado = detalleBD.get().getId();
		asientoDetService.eliminar(detalleBD.get());
		return new ResponseEntity<ErrorDto>(new ErrorDto("Cabecera del Asiento con id: "+ detalleEliminado + " eliminada satisfactoriamente."),HttpStatus.OK);
	}
	
	
	
}
