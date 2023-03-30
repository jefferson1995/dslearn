package com.devsuperior.dslearnbds.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dslearnbds.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email); //Busca no banco de dados um usuário passando e-mail como argumento
	
}
