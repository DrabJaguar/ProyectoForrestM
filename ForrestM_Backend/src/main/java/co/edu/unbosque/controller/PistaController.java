package co.edu.unbosque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.Emisora;
import co.edu.unbosque.model.Parrilla;
import co.edu.unbosque.model.Pista;
import co.edu.unbosque.repository.EmisoraRepository;
import co.edu.unbosque.repository.ParrillaRepository;
import co.edu.unbosque.repository.PistaRepository;

@RequestMapping("/api/pistas")
@RestController
	public class PistaController {

	    @Autowired
	    private PistaRepository pistaRepository;
	    private ParrillaRepository parrillaRepository;
	    
	    @GetMapping
	    Iterable<Pista>List(){
	    	return pistaRepository.findAll();
	    }
	    
	    
	    @PostMapping
	    public ResponseEntity<String> crearPista(@RequestBody Pista pista) {
	        try {
	            // Guardar la pista en la base de datos
	            pistaRepository.save(pista);
	            return new ResponseEntity<>("Pista creada con éxito", HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Error al crear la pista: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    
	    
	    
	   
}
