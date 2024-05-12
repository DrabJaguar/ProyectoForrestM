package co.edu.unbosque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.Emisora;
import co.edu.unbosque.model.Parrilla;
import co.edu.unbosque.repository.ParrillaRepository;

@RequestMapping("/api/parrillas")
@RestController
public class ParrillaController {

	@Autowired
	private ParrillaRepository parrillaRepository;
	
	@GetMapping
	Iterable<Parrilla> List(){
		return parrillaRepository.findAll();
	}
	
	@PostMapping
		 public ResponseEntity<String> crearParrilla(@RequestBody Parrilla parrilla) {
		        try {
		            Parrilla nuevaParrilla = parrillaRepository.save(parrilla);
		            return new ResponseEntity<>("Parrilla creada con éxito. ID: " + nuevaParrilla.getId_parrilla(), HttpStatus.CREATED);
		        } catch (Exception e) {
		            return new ResponseEntity<>("Error al crear la parrilla: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		        }
		    }
	
}
