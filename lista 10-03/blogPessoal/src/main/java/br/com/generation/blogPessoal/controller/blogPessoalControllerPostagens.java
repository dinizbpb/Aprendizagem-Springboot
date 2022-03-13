package br.com.generation.blogPessoal.controller;

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
import br.com.generation.blogPessoal.model.blogPessoalModelPostagens;
import br.com.generation.blogPessoal.repository.blogPessoalRepositoryPostagens;

@RestController

@RequestMapping(value = "/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class blogPessoalControllerPostagens {

	@Autowired
	private blogPessoalRepositoryPostagens repository;

//	gettar requisicao
	@GetMapping
	public ResponseEntity<List<blogPessoalModelPostagens>> findAllPostagem() {
		return ResponseEntity.ok(repository.findAll());

	}


	@GetMapping("/{id}")
	public ResponseEntity<blogPessoalModelPostagens> findByIdPostagem(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());

	}


	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<blogPessoalModelPostagens>> findByDescricaoTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));

	}

	//
	@PostMapping
	public ResponseEntity<blogPessoalModelPostagens> postPostagem(@RequestBody blogPessoalModelPostagens postagem) {

		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));

	}

	@PutMapping
	public ResponseEntity<blogPessoalModelPostagens> putPostagem(@RequestBody blogPessoalModelPostagens postagem) {

		return ResponseEntity.ok(repository.save(postagem));

	}

	@DeleteMapping("/{id}")
	public void deletePostagem(@PathVariable long id) {

		repository.deleteById(id);

	}

}
