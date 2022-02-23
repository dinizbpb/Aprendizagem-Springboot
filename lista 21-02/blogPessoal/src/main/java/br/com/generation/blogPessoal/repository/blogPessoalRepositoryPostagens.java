package br.com.generation.blogPessoal.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.generation.blogPessoal.model.blogPessoalModelPostagens;


@Repository // Avisa o spring que é um repository essa camada é responsavel pela comunicação com o DB

//recebe como parametro o nome do arquivo da model e sua importação, seu tipo é definido com letra maiuscula


public interface blogPessoalRepositoryPostagens extends JpaRepository<blogPessoalModelPostagens, Long> {
	//sempre herdar a interface da independencia jpa a JPARepository chamar a model e seu tipo

	public List<blogPessoalModelPostagens> findAllByTituloContainingIgnoreCase(String titulo); //quando usar lista importar import java.util.List;
	//cria uma lista com tudo que há no model.. Busca pelo titulo, tudo que contem nele, ignora as cases deixando tudo em minusculo
}

