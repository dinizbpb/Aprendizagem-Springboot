package br.com.generation.lojaDeGames.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.generation.lojaDeGames.model.lojaDeGamesModelProdutos;


@Repository 

public interface lojaDeGamesRepositoryProduto extends JpaRepository<lojaDeGamesModelProdutos, Long> {
	

	public List<lojaDeGamesModelProdutos> findAllByNomeContainingIgnoreCase(String titulo); 


}