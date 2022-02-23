package br.com.generation.lojaDeGames.model;


import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;




@Entity
@Table(name = "tb_produtos") 
public class lojaDeGamesModelProdutos {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	
	@NotNull
	@Size(min=2, max=100) 
	private String nome;

	@NotNull
	@Size(min=2, max=100)
	private String produtora;


	@ManyToOne 
	@JsonIgnoreProperties("produto")
	private lojaDeGamesModelCategoria categoria;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getProdutora() {
		return produtora;
	}


	public void setProdutora(String produtora) {
		this.produtora = produtora;
	}


	public lojaDeGamesModelCategoria getCategoria() {
		return categoria;
	}


	public void setCategoria(lojaDeGamesModelCategoria categoria) {
		this.categoria = categoria;
	}
			
	 

	
}
