package py.edu.ucsa.contabilidad.api.web.controllers;

import java.util.Optional;

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

import py.edu.ucsa.contabilidad.api.core.entities.AsientoCab;
import py.edu.ucsa.contabilidad.api.core.entities.AsientoDet;
import py.edu.ucsa.contabilidad.api.core.services.AsientoCabService;
import py.edu.ucsa.contabilidad.api.core.services.AsientoDetService;
import py.edu.ucsa.contabilidad.api.web.dto.AsientoDetDto;

@RestController
@RequestMapping("asientos-detalles")
public class AsientoDetController {
	private static final Logger logger = LoggerFactory.getLogger(AsientoDet.class);
	
	@Autowired
	@Qualifier("asientoDetService")
	private AsientoDetService asientoDetService;
//	@Autowired
//	private AsientoCabService asientoCabService;
	
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
	

	@PostMapping
	public ResponseEntity<?> crearAsientoDet(@RequestBody AsientoDet asientoDet, UriComponentsBuilder ucBuilder) {
//		AsientoDetDto dto = new AsientoDetDto();
//		dto.setAsientoCabId(asientoDet.getAsientoCab().getId());
//		Optional<AsientoCab> cabecera = Optional.ofNullable(new AsientoCab());
//		
//		cabecera = asientoCabService.getById(asientoDet.getAsientoCab().getId(), null);
//		String asientoCabDescripcion = cabecera.get().getDescripcion();
//		String asientoCabDescripcion = asientoDetService.     asientoDet.getAsientoCab().getId();
		
		logger.info("Creando el detalle  : {}", asientoDet.getId());
		AsientoDet insertado = asientoDetService.persistir(asientoDet);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/asientos-detalles/{id}").buildAndExpand(insertado.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	
}
