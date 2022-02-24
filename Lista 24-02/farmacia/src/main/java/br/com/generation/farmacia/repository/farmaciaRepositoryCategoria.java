package br.com.generation.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.generation.farmacia.model.farmaciaModelCategoria;

public interface farmaciaRepositoryCategoria extends JpaRepository<farmaciaModelCategoria, Long> {
	

	public List<farmaciaModelCategoria> findAllByDescricaoContainingIgnoreCase(String descricao); 

}


