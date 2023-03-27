package com.devsuperior.dslearnbds.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Esta classe ou ela vai ser Content ou Task (Abstract)
@Entity
@Table(name = "tb_lesson")
@Inheritance(strategy = InheritanceType.JOINED) //Cria uma tabela para cada classe TASK E CONTENT
public abstract class Lesson implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private Integer position;

	@ManyToOne
	@JoinColumn(name = "section_id")
	private Section section; // Está associada com 1 section

	// Existe um muito para muitos com a classe Enrollment para identificar a
	// matrícula do aluno

	//Cria uma tabela de associção com três colunas
	//Porque o id da classe Enrollment tem a chave composta por user e oferta
	@ManyToMany
	@JoinTable(name = "tb_lessons_done",
		joinColumns = @JoinColumn(name = "lesson_id"), //id classe atual
		inverseJoinColumns = {
			@JoinColumn(name = "user_id"),
			@JoinColumn(name = "offer_id")
		}
	)
	private Set<Enrollment> enrollmentsDone = new HashSet<>(); //Não adiciona a coleção em construtor e gerar apenas o get sem o set
	
	//Mapeamento da classe Deliver
	@OneToMany(mappedBy = "lesson")
	private List<Deliver> deliveries = new ArrayList<>();
	
	
	
	public Lesson() {
		
	}

	public Lesson(long id, String title, Integer position, Section section) {
		super();
		this.id = id;
		this.title = title;
		this.position = position;
		this.section = section;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Set<Enrollment> getEnrollmentsDone() {
		return enrollmentsDone;
	}
	
	//Apenas o get da lista - classe Deliver
	public List<Deliver> getDeliveries() {
		return deliveries;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		return id == other.id;
	}
	
	
	
	
	
}