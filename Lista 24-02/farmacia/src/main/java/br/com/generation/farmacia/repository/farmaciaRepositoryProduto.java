package br.com.generation.farmacia.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.generation.farmacia.model.farmaciaModelProdutos;


@Repository 

public interface farmaciaRepositoryProduto extends JpaRepository<farmaciaModelProdutos, Long> {
	

	public List<farmaciaModelProdutos> findAllByNomeContainingIgnoreCase(String titulo); 


}