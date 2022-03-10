package br.com.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import br.com.generation.blogPessoal.model.blogPessoalModelUsuario;         //com.generation.blogpessoal.model.Usuario;
import br.com.generation.blogPessoal.model.UserLogin;         //com.generation.blogpessoal.model.UsuarioLogin;
import br.com.generation.blogPessoal.repository.blogPessoalRepositoryUsuario;  //com.generation.blogpessoal.repository.UsuarioRepository;
import br.com.generation.blogPessoal.service.userService;   //blogpessoal.service.UsuarioService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {

	
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private userService usuarioService;

	@Autowired
	private blogPessoalRepositoryUsuario usuarioRepository;

	@BeforeAll
	void start(){

		

		usuarioRepository.deleteAll();

	}

	@Test
	@Order(1)
	@DisplayName("Cadastrar Um Usuário")
	public void deveCriarUmUsuario() {

		
		HttpEntity<blogPessoalModelUsuario> corpoRequisicao = new HttpEntity<blogPessoalModelUsuario>(new blogPessoalModelUsuario(0L, 
			"Paulo Antunes", "paulo_antunes@email.com.br", "13465278", "https://i.imgur.com/JR7kUFU.jpg"));

		
		ResponseEntity<blogPessoalModelUsuario> corpoResposta = testRestTemplate
			.exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, blogPessoalModelUsuario.class);

		
		assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());

		
		assertEquals(corpoRequisicao.getBody().getNome(), corpoResposta.getBody().getNome());

		
		assertEquals(corpoRequisicao.getBody().getUsuario(), corpoResposta.getBody().getUsuario());
	}

	@Test
	@Order(2)
	@DisplayName("Não deve permitir duplicação do Usuário")
	public void naoDeveDuplicarUsuario() {

		
		usuarioService.cadastrarUsuario(new blogPessoalModelUsuario(0L, 
			"Maria da Silva", "maria_silva@email.com.br", "13465278", "https://i.imgur.com/T12NIp9.jpg"));

		
		HttpEntity<blogPessoalModelUsuario> corpoRequisicao = new HttpEntity<blogPessoalModelUsuario>(new blogPessoalModelUsuario(0L, 
			"Maria da Silva", "maria_silva@email.com.br", "13465278", "https://i.imgur.com/T12NIp9.jpg"));

		
		ResponseEntity<blogPessoalModelUsuario> corpoResposta = testRestTemplate
			.exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, blogPessoalModelUsuario.class);

		
		assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());
	}

	@Test
	@Order(3)
	@DisplayName("Atualizar um Usuário")
	public void deveAtualizarUmUsuario() {

		
		Optional<blogPessoalModelUsuario> usuarioCadastrado = usuarioService.cadastrarUsuario(new blogPessoalModelUsuario(0L, 
			"Juliana Andrews", "juliana_andrews@email.com.br", "juliana123", "https://i.imgur.com/yDRVeK7.jpg"));
		
		blogPessoalModelUsuario usuarioUpdate = new blogPessoalModelUsuario(usuarioCadastrado.get().getId(), 
			"Juliana Andrews Ramos", "juliana_ramos@email.com.br", "juliana123" , "https://i.imgur.com/yDRVeK7.jpg");
		
		
		HttpEntity<blogPessoalModelUsuario> corpoRequisicao = new HttpEntity<blogPessoalModelUsuario>(usuarioUpdate);

		
		ResponseEntity<blogPessoalModelUsuario> corpoResposta = testRestTemplate
			.withBasicAuth("root", "root")
			.exchange("/usuarios/atualizar", HttpMethod.PUT, corpoRequisicao, blogPessoalModelUsuario.class);

		
		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());

		
		assertEquals(corpoRequisicao.getBody().getNome(), corpoResposta.getBody().getNome());

		
		assertEquals(corpoRequisicao.getBody().getUsuario(), corpoResposta.getBody().getUsuario());
	}

	@Test
	@Order(4)
	@DisplayName("Listar todos os Usuários")
	public void deveMostrarTodosUsuarios() {

		
		usuarioService.cadastrarUsuario(new blogPessoalModelUsuario(0L, 
			"Sabrina Sanches", "sabrina_sanches@email.com.br", "sabrina123", "https://i.imgur.com/5M2p5Wb.jpg"));
		
		usuarioService.cadastrarUsuario(new blogPessoalModelUsuario(0L, 
			"Ricardo Marques", "ricardo_marques@email.com.br", "ricardo123", "https://i.imgur.com/Sk5SjWE.jpg"));

		
		ResponseEntity<String> resposta = testRestTemplate
			.withBasicAuth("root", "root")
			.exchange("/usuarios/all", HttpMethod.GET, null, String.class);

		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());

	}

	

	@Test
	@Order(5)
	@DisplayName("Listar Um Usuário Específico")
	public void deveListarApenasUmUsuario() {
		
		
		
		Optional<blogPessoalModelUsuario> usuarioBusca = usuarioService.cadastrarUsuario(new blogPessoalModelUsuario(0L, 
				"Laura Santolia", "laura_santolia@email.com.br", "laura12345", "https://i.imgur.com/EcJG8kB.jpg"));
			
		
		ResponseEntity<String> resposta = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/usuarios/" + usuarioBusca.get().getId(), HttpMethod.GET, null, String.class);
		
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		
	}

	@Test
	@Order(6)
	@DisplayName("Login do Usuário")
	public void deveAutenticarUsuario() {

		
		usuarioService.cadastrarUsuario(new blogPessoalModelUsuario(0L, 
			"Marisa Souza", "marisa_souza@email.com.br", "13465278", "https://i.imgur.com/T12NIp9.jpg"));

		
		HttpEntity<UserLogin> corpoRequisicao = new HttpEntity<UserLogin>(new UserLogin(0L, 
			"", "marisa_souza@email.com.br", "13465278", "", ""));

		
		ResponseEntity<UserLogin> corpoResposta = testRestTemplate
			.exchange("/usuarios/logar", HttpMethod.POST, corpoRequisicao, UserLogin.class);

		
		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());

	}
}