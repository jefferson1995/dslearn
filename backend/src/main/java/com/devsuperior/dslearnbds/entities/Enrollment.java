package com.devsuperior.dslearnbds.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.devsuperior.dslearnbds.entities.pk.EnrollmentPK;

@Entity
@Table(name = "tb_enrollment")
public class Enrollment {

	@EmbeddedId  //Referencia do id da classe auxiliar EnrollmentPK
	private EnrollmentPK id = new EnrollmentPK(); //atributo identificador de uma classe de associação 
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") //Padrão UTC
	private Instant enrollMoment;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant refundMoment; //Quando a pessoa pede reembolso
	
	private boolean available;
	private boolean onlyUpdate; //Verifica se é um aluno normal(Quando tem suporte) ou de atualização(Sem suporte).
	
	@ManyToMany(mappedBy = "enrollmentsDone") // Nome do atributo associado
	private Set<Lesson> lessonsDone = new HashSet<>(); // Mapeamento para identificar quais usuários concluiram as lessons
	
	public Enrollment() {
		
	}

	public Enrollment(User user, Offer offer, Instant enrollMoment, Instant refundMoment, boolean available,
			boolean onlyUpdate) {
		super();
		//Para melhor entendimento, informar no construto que é o user e oferta
		id.setUser(user);
		id.setOffer(offer);
		this.enrollMoment = enrollMoment;
		this.refundMoment = refundMoment;
		this.available = available;
		this.onlyUpdate = onlyUpdate;
	}

	/*
	public EnrollmentPK getId() {
		return id;
	}

	public void setId(EnrollmentPK id) {
		this.id = id;
	}
	 - Desativado para Pegar o usuário e a oferta
	*/
	
	//Para pegar quem é o aluno matrículado
	public User getStudent() {
		return id.getUser();
	}
	
	public void setStudent(User user) {
		id.setUser(user);
	}
	
	
	//Para pegar a oferta
	
	public Offer getOffer() {
		return id.getOffer();
	}
	
	public void setOffer(Offer offer) {
		id.setOffer(offer);
	}
	
	

	public Instant getEnrollMoment() {
		return enrollMoment;
	}

	public void setEnrollMoment(Instant enrollMoment) {
		this.enrollMoment = enrollMoment;
	}

	public Instant getRefundMoment() {
		return refundMoment;
	}

	public void setRefundMoment(Instant refundMoment) {
		this.refundMoment = refundMoment;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public boolean isOnlyUpdate() {
		return onlyUpdate;
	}

	public void setOnlyUpdate(boolean onlyUpdate) {
		this.onlyUpdate = onlyUpdate;
	}
	
	
	
	
	
}
