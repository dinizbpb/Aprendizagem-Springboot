package br.com.generation.farmacia.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.generation.farmacia.model.farmaciaModelCategoria;
import br.com.generation.farmacia.repository.farmaciaRepositoryCategoria;

@RestController 
@RequestMapping("/catprodutos")
@CrossOrigin(origins = "*", allowedHeaders = "*" ) 

public class farmaciaControllerCategoria {

	@Autowired
	private farmaciaRepositoryCategoria repository;
	
	
	@GetMapping
	public ResponseEntity<List<farmaciaModelCategoria>> GetAll(){   
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<farmaciaModelCategoria> GetById(@PathVariable long id){ 
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());	
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<farmaciaModelCategoria>> GetByDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
											
	}
	
	@PostMapping
	public ResponseEntity<farmaciaModelCategoria> Post (@RequestBody farmaciaModelCategoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<farmaciaModelCategoria> Put(@RequestBody farmaciaModelCategoria categoria){
		return ResponseEntity.status(HttpStatus.OK)
				.body(repository.save(categoria));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
		  
	
	
	
	
	
	
}
