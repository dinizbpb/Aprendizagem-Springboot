package br.com.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;




@Entity //essa classe será uma entidade do jpa para falar para o spring que é uma model
@Table(name = "postagens") // cria uma tabela com o nome postagens e coloca os atributos da entidade blogPessoalModelPostagens
public class blogPessoalModelPostagens {
	
	@Id  //cria uma id sql
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment e chave primária  do sql 
	private long id;
	
	@NotNull
	@Size(min=2, max=100) //se der erro colocar a dependência validation
	private String titulo;

	@NotNull
	@Size(min=2, max=100)
	private String texto;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());

	
	
	 public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
