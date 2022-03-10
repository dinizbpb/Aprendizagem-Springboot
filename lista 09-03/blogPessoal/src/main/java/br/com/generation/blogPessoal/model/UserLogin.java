package br.com.generation.blogPessoal.model;

// essa camda se refere ao login, não vai para o banco de dados

public class UserLogin {
	
	private Long id;
	
	private String nome;
	
	private String usuario;
	
	private String senha;
	
	private String token;  //atributo que dá acesso ao usuario quando ele logar na plataforma

	
	
	
	public UserLogin(Long id, String nome, String usuario, String senha, String token) {
		super();
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.token = token;
	}

	public UserLogin() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
	

}
