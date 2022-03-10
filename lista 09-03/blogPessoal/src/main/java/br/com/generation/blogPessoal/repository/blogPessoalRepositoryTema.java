package br.com.generation.blogPessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.generation.blogPessoal.model.blogPessoalModelTema;

public interface blogPessoalRepositoryTema extends JpaRepository<blogPessoalModelTema, Long> {
	

	public List<blogPessoalModelTema> findAllByDescricaoContainingIgnoreCase(String descricao); 

}


