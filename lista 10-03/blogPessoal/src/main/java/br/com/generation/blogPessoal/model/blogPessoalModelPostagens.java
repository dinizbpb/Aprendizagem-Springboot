package br.com.generation.blogPessoal.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;




@Entity //essa classe será uma entidade do jpa para falar para o spring que é uma model
@Table(name = "tb_postagens") // cria uma tabela com o nome postagens e coloca os atributos da entidade blogPessoalModelPostagens
public class blogPessoalModelPostagens {
	
	@Id  //cria uma id sql
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment e chave primária  do sql 
	private long id;
	
	
	@Size(min=2, max=100) //se der erro colocar a dependência validation
	private String titulo;

	
	@Size(min=2, max=100)
	private String texto;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());

	@ManyToOne //muitas postagens pra um tema
	@JsonIgnoreProperties("postagem") // pega o nome do metodo/função da logPessoalModelTema...private List<blogPessoalModelPostagens> postagem;
	private blogPessoalModelTema tema;
			//chama a model tema //nome do metodo/função
	 
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private blogPessoalModelUsuario usuario;
	
	
	
	
	
	

	public blogPessoalModelPostagens(long id, @Size(min = 2, max = 100) String titulo,
					@Size(min = 2, max = 100) String texto, Date data, blogPessoalModelTema tema,
					blogPessoalModelUsuario usuario) {
				
				this.id = id;
				this.titulo = titulo;
				this.texto = texto;
				this.data = data;
				this.tema = tema;
				this.usuario = usuario;
			}
	
	public blogPessoalModelPostagens() {}
	

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
	
	public blogPessoalModelTema getTema() {
		return tema;
	}

	public void setTema(blogPessoalModelTema tema) {
		this.tema = tema;
	}
}
