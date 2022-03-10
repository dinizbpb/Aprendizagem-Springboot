package br.com.generation.blogPessoal.controller;

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
import br.com.generation.blogPessoal.service.userService;



@RestController 
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*" ) 


	public class blogPessoalControllerUsuario {
	
	
		@Autowired
		private userService usuarioService; //não vai na camada de repository e sim na de service (bnão chama repository)
		
		
		
		
		@PostMapping("/logar")
		public ResponseEntity<UserLogin> Autentication (@RequestBody Optional<UserLogin> user){
			return  usuarioService.autenticarUsuario(user).map(resp -> ResponseEntity.ok(resp))
					.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
					
		}
		
		
		@PostMapping("/cadastrar")
		public ResponseEntity<Optional<blogPessoalModelUsuario>> post (@RequestBody blogPessoalModelUsuario usuario){
			return  ResponseEntity.status(HttpStatus.CREATED)
					.body(usuarioService.cadastrarUsuario(usuario));
					
		}
		
		@PutMapping("/atualizar")
		public ResponseEntity<blogPessoalModelUsuario> putUsuario(@Valid @RequestBody blogPessoalModelUsuario usuario) {
			return usuarioService.atualizarUsuario(usuario)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}
		
		
	}
