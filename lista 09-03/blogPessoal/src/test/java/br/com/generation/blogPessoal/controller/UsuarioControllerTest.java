package br.com.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

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

import br.com.generation.blogPessoal.model.UserLogin;
import br.com.generation.blogPessoal.model.blogPessoalModelUsuario;
import br.com.generation.blogPessoal.repository.blogPessoalRepositoryUsuario;
import br.com.generation.blogPessoal.service.userService;

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
			"Paulo Antunes", "paulo_antunes@email.com.br", "1346527899"));

		
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
			"Maria da Silva", "maria_silva@email.com.br", "1346527899"));

		
		HttpEntity<blogPessoalModelUsuario> corpoRequisicao = new HttpEntity<blogPessoalModelUsuario>(new blogPessoalModelUsuario(0L, 
			"Maria da Silva", "maria_silva@email.com.br", "1346527899"));

		
		ResponseEntity<blogPessoalModelUsuario> corpoResposta = testRestTemplate
			.exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, blogPessoalModelUsuario.class);

		
		assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());
	}

	@Test
	@Order(3)
	@DisplayName("Atualizar um Usuário")
	public void deveAtualizarUmUsuario() {

		
		Optional<blogPessoalModelUsuario> usuarioCadastrado = usuarioService.cadastrarUsuario(new blogPessoalModelUsuario(0L, 
			"Juliana Andrews", "juliana_andrews@email.com.br", "juliana123"));
		
		blogPessoalModelUsuario usuarioUpdate = new blogPessoalModelUsuario(usuarioCadastrado.get().getId(), 
			"Juliana Andrews Ramos", "juliana_ramos@email.com.br", "juliana123");
		
		
		HttpEntity<blogPessoalModelUsuario> corpoRequisicao = new HttpEntity<blogPessoalModelUsuario>(usuarioUpdate);

		
		ResponseEntity<blogPessoalModelUsuario> corpoResposta = testRestTemplate
			.withBasicAuth("root", "060595")
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
			"Sabrina Sanches", "sabrina_sanches@email.com.br", "sabrina1234"));
		
		usuarioService.cadastrarUsuario(new blogPessoalModelUsuario(0L, 
			"Ricardo Marques", "ricardo_marques@email.com.br", "ricardo123"));

		
		ResponseEntity<String> resposta = testRestTemplate
			.withBasicAuth("root", "060595")
			.exchange("/usuarios/all", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());

	}

	

	@Test
	@Order(5)
	@DisplayName("Listar Um Usuário Específico")
	public void deveListarApenasUmUsuario() {
		
		
		Optional<blogPessoalModelUsuario> usuarioBusca = usuarioService.cadastrarUsuario(new blogPessoalModelUsuario(0L, 
				"Laura Santolia", "laura_santolia@email.com.br", "laura12345"));
			
		
		ResponseEntity<String> resposta = testRestTemplate
				.withBasicAuth("root", "060595")
				.exchange("/usuarios/" + usuarioBusca.get().getId(), HttpMethod.GET, null, String.class);
		
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		
	}

	@Test
	@Order(6)
	@DisplayName("Login do Usuário")
	public void deveAutenticarUsuario() {

		
		usuarioService.cadastrarUsuario(new blogPessoalModelUsuario(0L, 
			"Marisa Souza", "marisa_souza@email.com.br", "13465278"));

		
		HttpEntity<blogPessoalModelUsuario> corpoRequisicao = new HttpEntity<blogPessoalModelUsuario>(new blogPessoalModelUsuario(0L, 
			"maria", "marisa_souza@email.com.br", "1346527899"));

		
		ResponseEntity<UserLogin> corpoResposta = testRestTemplate
			.exchange("/usuarios/logar", HttpMethod.POST, corpoRequisicao, UserLogin.class);

		
		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());

	}
}