package tn.esprit.spring.entity.Cv;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import tn.esprit.spring.entity.Offre.Candidature;
import tn.esprit.spring.entity.User.User;

@Entity
@JsonIgnoreProperties
public class CvCandidat implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String Cv;
	@ManyToOne
	@JsonIgnore
	private User userId;
	
	
	
	
	public CvCandidat() {
		super();
	}
	
	
	public CvCandidat(Long id, String cv, User userId) {
		super();
		Id = id;
		Cv = cv;
		this.userId = userId;
	}
	
	


	


	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getCv() {
		return Cv;
	}
	public void setCv(String cv) {
		Cv = cv;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	
	

}
