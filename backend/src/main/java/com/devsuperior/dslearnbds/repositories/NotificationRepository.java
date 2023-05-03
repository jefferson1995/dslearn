package com.devsuperior.dslearnbds.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dslearnbds.entities.Notification;
import com.devsuperior.dslearnbds.entities.User;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

	/*
	 * Desativado para usar JPQL
	 
	//Método para retornar as notificações paginadas de um determinado usuário 
	Page<Notification> findByUser(User user, Pageable pageable); //Aplica tbm os parametros de paginação
	*/
	
	
	//JPQL para ter opção de trazer somente as notificações não lidas
	@Query("SELECT obj FROM Notification obj WHERE "
			+ "(obj.user = :user) AND " //Se unreadOnly for false = busca somente as não lidas 
			+ "(:unreadOnly = false OR obj.read = false) " //Se unreadOnly for verdadeiro busca todas notificação
			+ "ORDER BY obj.moment DESC") //Da mais recente para mais antiga
	Page<Notification> find(User user,boolean unreadOnly, Pageable pageable);
	
}
