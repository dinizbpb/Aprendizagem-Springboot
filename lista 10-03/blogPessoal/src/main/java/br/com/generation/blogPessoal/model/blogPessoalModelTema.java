package br.com.generation.blogPessoal.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name = "tb_tema")
public class blogPessoalModelTema {

	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	
	
	private String descricao;
	
	//um tema pra muitas postagens
	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL) //// pega o nome do metodo/função da logPessoalModelPostagens...private private blogPessoalModelTema tema;
	@JsonIgnoreProperties("tema")
	private List<blogPessoalModelPostagens> postagem;
			//herda a lista de postagens// nome do metodo/ função
	
	
	
	public blogPessoalModelTema(long id, String descricao, List<blogPessoalModelPostagens> postagem) {
		
		this.id = id;
		this.descricao = descricao;
		this.postagem = postagem;
	}

	
	public blogPessoalModelTema() {}
	
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public List<blogPessoalModelPostagens> getPostagem() {
		return postagem;
	}


	public void setPostagem(List<blogPessoalModelPostagens> postagem) {
		this.postagem = postagem;
	}

	
	
	
	
	}

	

	
	

