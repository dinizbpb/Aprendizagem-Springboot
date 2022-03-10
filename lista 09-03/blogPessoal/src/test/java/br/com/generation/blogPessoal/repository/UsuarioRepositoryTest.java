package br.com.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import br.com.generation.blogPessoal.model.blogPessoalModelUsuario;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
    
	@Autowired
	private blogPessoalRepositoryUsuario usuarioRepository;
	
	@BeforeAll
	void start(){

		

		usuarioRepository.deleteAll();
		

		usuarioRepository.save(new blogPessoalModelUsuario(0L, "João da Silva", "joao@email.com.br", "1346527899"));
		
		usuarioRepository.save(new blogPessoalModelUsuario(0L, "Manuela da Silva", "manuela@email.com.br", "1346527888"));
		
		usuarioRepository.save(new blogPessoalModelUsuario(0L, "Adriana da Silva", "adriana@email.com.br", "1346527888"));

        usuarioRepository.save(new blogPessoalModelUsuario(0L, "Paulo Antunes", "paulo@email.com.br", "134652789"));

	}

	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {

		
		Optional<blogPessoalModelUsuario> usuario = usuarioRepository.findByUsuario("joao@email.com.br");

		
		assertTrue(usuario.get().getUsuario().equals("joao@email.com.br"));
	}

	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {

		
		Optional<blogPessoalModelUsuario> listaDeUsuarios = usuarioRepository.findByUsuario("Silva");

		
		assertEquals(3, listaDeUsuarios);

		
		 
		assertTrue(listaDeUsuarios.get().getNome().equals("João da Silva"));

		
		assertTrue(listaDeUsuarios.get().getNome().equals("Manuela da Silva"));

		
		assertTrue(listaDeUsuarios.get().getNome().equals("Adriana da Silva"));
		
	}

	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}
	
}