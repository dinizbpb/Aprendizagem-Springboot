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

import br.com.generation.farmacia.model.farmaciaModelProdutos;
import br.com.generation.farmacia.repository.farmaciaRepositoryProduto;

@RestController 
@RequestMapping("/produtos") 
@CrossOrigin(origins = "*", allowedHeaders = "*" ) 



public class farmaciaControllerProduto {
	
	
	
	@Autowired
	private farmaciaRepositoryProduto repository;
		  
	
	
	
	@GetMapping 
	public ResponseEntity<List<farmaciaModelProdutos>> GetAll(){ 
		    
		return ResponseEntity.ok(repository.findAll());
		
	}
	
	
	@GetMapping("/{id}") 
	public ResponseEntity<farmaciaModelProdutos> GetById(@PathVariable long id){ 
		
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
			
	}
	
	
	
	@GetMapping("/nome/{nomeJogo}") 
	public ResponseEntity<List<farmaciaModelProdutos>> GetByNome(@PathVariable String nomeJogo){
						
		
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nomeJogo));
											
	}
	
	
	
	
	
	@PostMapping
	public ResponseEntity<farmaciaModelProdutos> Post (@RequestBody farmaciaModelProdutos produtos){
		return ResponseEntity.status(HttpStatus.CREATED)  //receber via body(corpo) parecido com o @PathVariable
				.body(repository.save(produtos));
	}
	
	
	

	
	@PutMapping
	public ResponseEntity<farmaciaModelProdutos> Put(@RequestBody farmaciaModelProdutos produtos){
		return ResponseEntity.status(HttpStatus.OK)		
				.body(repository.save(produtos));
	}
	
	
	@DeleteMapping("/{id}")
	
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	
	
	
}

 

