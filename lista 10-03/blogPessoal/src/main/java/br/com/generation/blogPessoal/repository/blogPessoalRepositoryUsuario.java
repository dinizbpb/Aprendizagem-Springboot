package br.com.generation.blogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.generation.blogPessoal.model.blogPessoalModelUsuario;

public interface blogPessoalRepositoryUsuario extends JpaRepository<blogPessoalModelUsuario, Long> {
	
	
	public Optional<blogPessoalModelUsuario> findByUsuario(String usuario);
	
	public List<blogPessoalModelUsuario> findAllByNomeContainingIgnoreCase(String string);
	
	
}
