package blogPessoalv2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blogPessoalv2.model.Tema;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long>{
public List<Tema> findAllByNomeContainingIgnoreCase(String nome);
}
