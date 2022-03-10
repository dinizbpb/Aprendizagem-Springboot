package br.com.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.generation.blogPessoal.model.UserLogin;
import br.com.generation.blogPessoal.model.blogPessoalModelUsuario;
import br.com.generation.blogPessoal.repository.blogPessoalRepositoryUsuario;
import br.com.generation.blogPessoal.service.userService;



@RestController 
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*" ) 


	public class blogPessoalControllerUsuario {
	
	
		@Autowired
		private userService usuarioService; //não vai na camada de repository e sim na de service (bnão chama repository)
		
		@Autowired
		private blogPessoalRepositoryUsuario usuarioRepository;
		
		@GetMapping("/all")
		public ResponseEntity <List<blogPessoalModelUsuario>> getAll(){
			
			return ResponseEntity.ok(usuarioRepository.findAll());
			
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<blogPessoalModelUsuario> getById(@PathVariable Long id) {
			return usuarioRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
		}
		
		@PostMapping("/logar")
		public ResponseEntity<UserLogin> login(@RequestBody Optional<UserLogin> usuarioLogin) {
			return usuarioService.autenticarUsuario(usuarioLogin)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
		}

		@PostMapping("/cadastrar")
		public ResponseEntity<blogPessoalModelUsuario> postUsuario(@Valid @RequestBody blogPessoalModelUsuario usuario) {

			return usuarioService.cadastrarUsuario(usuario)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

		}

		@PutMapping("/atualizar")
		public ResponseEntity<blogPessoalModelUsuario> putUsuario(@Valid @RequestBody blogPessoalModelUsuario usuario) {
			return usuarioService.atualizarUsuario(usuario)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}

		
	}
