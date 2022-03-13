package br.com.generation.lojaDeGames.model;

// essa camda se refere ao login, não vai para o banco de dados

public class UserLogin {
	
	private Long id;
	
	private String nome;
	
	private String usuario;
	
	private String senha;
	
	private String foto;
	
	private String token;  //atributo que dá acesso ao usuario quando ele logar na plataforma

	
	
	
	public UserLogin(Long id, String nome, String usuario, String senha, String token) {
		super();
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.token = token;
	}

	public UserLogin(Long id, String nome, String usuario, String senha, String foto, String token) {
		this.id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
		this.foto = foto;
		this.token = token;
	}

	public UserLogin() {	}

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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}	

}