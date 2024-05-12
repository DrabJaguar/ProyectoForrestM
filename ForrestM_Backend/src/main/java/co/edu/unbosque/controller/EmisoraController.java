package co.edu.unbosque.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.Emisora;
import co.edu.unbosque.repository.EmisoraRepository;

@RequestMapping("/api/emisoras")
@RestController
public class EmisoraController {

	@Autowired
	private EmisoraRepository emisoraRepository;
	
	@GetMapping
	Iterable<Emisora> List(){
		return emisoraRepository.findAll();
	}
	
	  @PostMapping
	    public ResponseEntity<String> crearEmisora(@RequestBody Emisora emisora) {
	        try {
	            Emisora nuevaEmisora = emisoraRepository.save(emisora);
	            return new ResponseEntity<>("Emisora creada con éxito. ID: " + nuevaEmisora.getId_emisora(), HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Error al crear la emisora: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	  
	  
	  @GetMapping("/{id}")
	  public ResponseEntity<?> buscarPorId(@PathVariable Integer id) {
	      try {
	          Optional<Emisora> emisoraOptional = emisoraRepository.findById(id);
	          if (emisoraOptional.isPresent()) {
	              Emisora emisora = emisoraOptional.get();
	              return new ResponseEntity<>(emisora, HttpStatus.OK);
	          } else {
	              return new ResponseEntity<>("No se encontró ninguna emisora con el ID: " + id, HttpStatus.NOT_FOUND);
	          }
	      } catch (Exception e) {
	          return new ResponseEntity<>("Error al buscar la emisora por ID: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	  }
	
}
