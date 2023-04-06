package com.devsuperior.dslearnbds.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslearnbds.dto.UserDTO;
import com.devsuperior.dslearnbds.entities.User;
import com.devsuperior.dslearnbds.repositories.UserRepository;
import com.devsuperior.dslearnbds.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class); //Para pegar os logs da aplicação
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AuthService authService;
	
	//Método para buscar usuário por id
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		
		//Antes de fazer a operação, faz a validação, se lançar exeção a requisição é suspensa
		authService.validateSelfOrAdmin(id);
		
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Nenhum id encontrado"));

		return new UserDTO(entity);
	}
	
	//Método do UserDetails para encontrar o e-mail 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repository.findByEmail(username);
		//Caso não encontre o e-mail do usuário, estoura uma exceção
		if(user == null) {
			logger.error("Usuário não encontrado: " + username);//Para lançar o log no console da aplicação
			throw new UsernameNotFoundException("E-mail não encontrado");
		}
		logger.info("User found: " + username); //Para lançar o log no console da aplicação
		return user;
	}
}
