package br.com.generation.lojaDeGames.security;

import java.util.Optional;

import br.com.generation.lojaDeGames.model.lojaDeGamesModelUsuario;  //com.generation.blogpessoal.model.Usuario;
import br.com.generation.lojaDeGames.repository.lojaDeGamesRepositoryUsuario;  //com.generation.blogpessoal.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private lojaDeGamesRepositoryUsuario userRepository;

	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		
		
		Optional<lojaDeGamesModelUsuario> usuario = userRepository.findByUsuario(userName);
		
		
	  
		usuario.orElseThrow(() -> new UsernameNotFoundException(userName + " not found."));

		

		return usuario.map(UserDetailsImpl::new).get();
	}
}
