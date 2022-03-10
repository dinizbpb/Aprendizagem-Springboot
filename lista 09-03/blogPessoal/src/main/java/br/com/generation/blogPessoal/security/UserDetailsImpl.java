package br.com.generation.blogPessoal.security;

import java.util.Collection;
import java.util.List;

import br.com.generation.blogPessoal.model.blogPessoalModelUsuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;

	/**
	 * Método construtor com parâmetros 
	 * 
	 * Observe que este método Construtor recebe um objeto Usuario e
	 * recupera os dados necessários através dos respectivos métodos Get
	 */

	public UserDetailsImpl(blogPessoalModelUsuario usuario) {
		this.userName = usuario.getUsuario();
		this.password = usuario.getSenha();
	}


	public UserDetailsImpl() {	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {

		return userName;
	}

	

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	

	@Override
	public boolean isEnabled() {
		return true;
	}
}