package br.com.generation.lojaDeGames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.generation.lojaDeGames.model.lojaDeGamesModelCategoria;

public interface lojaDeGamesRepositoryCategoria extends JpaRepository<lojaDeGamesModelCategoria, Long> {
	

	public List<lojaDeGamesModelCategoria> findAllByDescricaoContainingIgnoreCase(String descricao); 

}


