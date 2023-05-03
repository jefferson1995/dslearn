package com.devsuperior.dslearnbds.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dslearnbds.dto.NotificationDTO;
import com.devsuperior.dslearnbds.entities.Notification;
import com.devsuperior.dslearnbds.entities.User;
import com.devsuperior.dslearnbds.repositories.NotificationRepository;

@Service
public class NotificationService {

	@Autowired
	private NotificationRepository repository;
	
	@Autowired
	private AuthService authService;
	
	//Método para buscar a notificação de um usuário especifico
	public Page<NotificationDTO> notificationsForCurrentUser(boolean unreadOnly, Pageable pageable){
		User user = authService.authenticated(); //Busca user autenticado
		Page<Notification> page = repository.find(user, unreadOnly, pageable); //Busca notficação paginada
		return page.map(x -> new NotificationDTO(x));	
	}
}
