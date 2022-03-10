package br.com.generation.blogPessoal.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.generation.blogPessoal.model.blogPessoalModelUsuario;
import br.com.generation.blogPessoal.repository.blogPessoalRepositoryUsuario;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private blogPessoalRepositoryUsuario blogPessoalRepositoryUsuario;
	
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Optional<blogPessoalModelUsuario> user = blogPessoalRepositoryUsuario.findByUsuario(userName);
		user.orElseThrow(() -> new UsernameNotFoundException(userName + "not found."));
		
		return user.map(UserDetailsImpl::new).get();
	}
	
	
	
} 
