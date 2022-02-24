package br.com.generation.farmacia.model;



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
public class farmaciaModelProdutos {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	
	@NotNull
	@Size(min=2, max=100) 
	private String nome;

	@NotNull
	@Size(min=2, max=100)
	private String fabricante;


	@ManyToOne 
	@JsonIgnoreProperties("produto")
	private farmaciaModelCategoria categoria;


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
		return fabricante;
	}


	public void setProdutora(String fabricante) {
		this.fabricante = fabricante;
	}


	public farmaciaModelCategoria getCategoria() {
		return categoria;
	}


	public void setCategoria(farmaciaModelCategoria categoria) {
		this.categoria = categoria;
	}
			
	 

	
}
