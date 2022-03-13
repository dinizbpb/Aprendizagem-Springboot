package br.com.generation.lojaDeGames.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.generation.lojaDeGames.model.lojaDeGamesModelUsuario;

public interface lojaDeGamesRepositoryUsuario extends JpaRepository<lojaDeGamesModelUsuario, Long> {
	
	
	public Optional<lojaDeGamesModelUsuario> findByUsuario(String usuario);
	
	public List<lojaDeGamesModelUsuario> findAllByNomeContainingIgnoreCase(String string);
	
	
}
