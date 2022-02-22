package br.com.generation.blogPessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.generation.blogPessoal.model.blogPessoalModelPostagens;
import br.com.generation.blogPessoal.repository.blogPessoalRepositoryPostagens;

@RestController //spring saber que é um controller, essa camada é responsável pela comunicação com o front(postman, angular)
@RequestMapping("/blogpessoal") //endereço do diretorio para o front acessar a api
@CrossOrigin(origins = "*", allowedHeaders = "*" ) //para aceitar requisições de qualquer origem (angular, react e etc..)



public class blogPessoalControllerPostagens {
	
	
	
	@Autowired//por se tratar de uma interface não dá pra instanciar então se usa o @autowired para injeção de indempendencia 
	private blogPessoalRepositoryPostagens repository;
		  //injetar e Chamar a interface do repositório e por o nome dele de repository
	
	
	
	@GetMapping // sempre que chamar o url com get esse metodo é disparado
	public ResponseEntity<List<blogPessoalModelPostagens>> GetAll(){ //GetAll nome do metodo/ função 
		    //tipo	     //lista//parametro da lista é do tipo model e chama o model correspondente  //pega tudo dele
		return ResponseEntity.ok(repository.findAll());
		// a resposta será um objeto com o status ok e o objeto repository (@Autowired) com tudo dele
	}
	
	
	@GetMapping("/{id}") //parametro que está eperando para chamar esse metodo (chama pelo id do DB)
	public ResponseEntity<blogPessoalModelPostagens> GetById(@PathVariable long id){ //@PathVariable Quando queremos passar um valor pela URI (URL), recepcionamos o valor através dessa anotação
			//tipo	     parametro da lista é do tipo model e chama o model correspondente  // GetById chama pelo id // @PathVariable captura o valor da variavel digitada no end point@GetMapping("/{id}") e o tipo dela tem que ser igual no model (DB)
		//GetById(@PathVariable long id)... GetById é o nome do metodo /função  (parametros)
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
			//retorna a interface criada com o @Autowired no caso a repository
			//usa o metodo fingById para retornar um objeto do tipo postagem com ok, ou bad caso dê ruim 	
	}
	
	
	//end point para GET Buscando pelo Titulo
	@GetMapping("/titulo/{titulo}") // colocar o /titulo para não dar redundancia com o id de cima depois do /titulo/2 para chamar o titulo 2.
	public ResponseEntity<List<blogPessoalModelPostagens>> GetByTitulo(@PathVariable String titulo){
						// parametro da lista é do tipo model e chama o model correspondente //GetByTitulo é nome do metodo /função (seus parametros)
		
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
											//ignora letra maiscula e minuscula na busca
	}
	
	
	
	//Metodo post inserir dados
	
	@PostMapping//Sempre quando formos realizar um Post/Put enviaremos os dados via Body
	public ResponseEntity<blogPessoalModelPostagens> Post (@RequestBody blogPessoalModelPostagens postagem){
		return ResponseEntity.status(HttpStatus.CREATED)  //receber via body(corpo) parecido com o @PathVariable
				.body(repository.save(postagem));
	}
	
	
	
	//Metodo put atualizar os dados
	
	@PutMapping
	public ResponseEntity<blogPessoalModelPostagens> Put(@RequestBody blogPessoalModelPostagens postagem){
		return ResponseEntity.status(HttpStatus.OK)		//receber via body(corpo) parecido com o @PathVariable
				.body(repository.save(postagem));
	}
	
	//Metodo delete
	
	@DeleteMapping("/{id}")
	
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	
	
	
}

 

