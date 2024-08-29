package py.edu.ucsa.contabilidad.api.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import py.edu.ucsa.contabilidad.api.core.services.CobroService;
import py.edu.ucsa.contabilidad.api.web.dto.CobroDto;
import py.edu.ucsa.contabilidad.api.web.dto.ErrorDto;

@RestController
@RequestMapping("/cobros")
public class CobroController {

    @Autowired
    @Qualifier("cobroService")
    private CobroService cobroService;

    @GetMapping
    public ResponseEntity<List<CobroDto>> listar(){
    	List<CobroDto> cobros = cobroService.listar();
    	return new ResponseEntity<>(cobros,HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CobroDto> GetById(@PathVariable Long id){
    	CobroDto cobro = cobroService.getById(id);
    	return new ResponseEntity<>(cobro,HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<CobroDto> persistir(@RequestBody CobroDto cobroDto){
    	CobroDto nuevoCobro = cobroService.persistir(cobroDto);
    	return new ResponseEntity<>(nuevoCobro,HttpStatus.CREATED);
    }
    
    @PutMapping
    public ResponseEntity<CobroDto> actualizar(@PathVariable Long id, @RequestBody CobroDto cobroDto){
    	cobroDto.setId(id);
    	CobroDto cobroActualizado = cobroService.actualizar(cobroDto);
    	return new ResponseEntity<>(cobroActualizado,HttpStatus.OK);
    	
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
    	CobroDto cobroEliminado = cobroService.getById(id);
    	cobroService.eliminar(cobroEliminado);
    	return new ResponseEntity<ErrorDto>(new ErrorDto("Cobro: "+ cobroEliminado + " eliminado satisfactoriamente."),HttpStatus.OK);
    }
    
}
