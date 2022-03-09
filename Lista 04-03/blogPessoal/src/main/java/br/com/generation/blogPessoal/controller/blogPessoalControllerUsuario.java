package br.com.generation.blogPessoal.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.generation.blogPessoal.model.UserLogin;
import br.com.generation.blogPessoal.service.userService;
import br.com.generation.blogPessoal.model.blogPessoalModelUsuario;



@RestController 
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*" ) 


	public class blogPessoalControllerUsuario {
	
	
		@Autowired
		private userService usuarioService; //não vai na camada de repository e sim na de service (bnão chama repository)
		
		@PostMapping("/logar")
		public ResponseEntity<UserLogin> Autentication (@RequestBody Optional<UserLogin> user){
			return  usuarioService.logar(user).map(resp -> ResponseEntity.ok(resp))
					.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
					
		}
		
		
		@PostMapping("/cadastrar")
		public ResponseEntity<blogPessoalModelUsuario> post (@RequestBody blogPessoalModelUsuario usuario){
			return  ResponseEntity.status(HttpStatus.CREATED)
					.body(usuarioService.cadastrarUsuario(usuario));
					
		}
		
		
		
		
	}
