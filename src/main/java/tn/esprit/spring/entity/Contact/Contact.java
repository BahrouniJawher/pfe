package tn.esprit.spring.entity.Contact;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@Entity
@Table(name = "contact")
@JsonIgnoreProperties
public class Contact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7950217207447904668L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull (message="your name is null")
	@Column(name = "nom")
	private String nom;
    protected String email;
    protected Long num_tel;
    @NotNull
	@Column(name = "description")
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getNum_tel() {
		return num_tel;
	}
	public void setNum_tel(Long num_tel) {
		this.num_tel = num_tel;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Contact(Long id, String nom, String email, Long num_tel, String description) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.num_tel = num_tel;
		this.description = description;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", nom=" + nom + ", email=" + email + ", num_tel=" + num_tel + ", description="
				+ description + "]";
	}
	
	



	

}
