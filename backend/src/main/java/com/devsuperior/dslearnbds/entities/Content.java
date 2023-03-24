package com.devsuperior.dslearnbds.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

//Precisa colocar apenas o nome da tabela, porque é uma classe de herança
@Entity
@Table(name = "tb_content")
public class Content extends Lesson{

	private static final long serialVersionUID = 1L;
	
	private String textContent;
	private String videoUri;
	
	public Content() {
		
	}
	//Recebe todos argumentos da classe super Lesson, mais os da classe atual Content 
	public Content(long id, String title, Integer position, Section section, String textContent, String videoUri) {
		super(id, title, position, section);
		this.textContent = textContent;
		this.videoUri = videoUri;
	}
	public String getTextContent() {
		return textContent;
	}
	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	public String getVideoUri() {
		return videoUri;
	}
	public void setVideoUri(String videoUri) {
		this.videoUri = videoUri;
	}
	
	//Não é preciso adicionar o hashCode and Equals, porque a classe super já tem

	
}
