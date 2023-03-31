package com.devsuperior.dslearnbds.dto;

import java.io.Serializable;

import com.devsuperior.dslearnbds.entities.User;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String email;
	
	public UserDTO() {
		
	}

	public UserDTO(long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	//Converte o user para DTO
	public UserDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
	
}
