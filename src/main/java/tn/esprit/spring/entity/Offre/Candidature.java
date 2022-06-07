package tn.esprit.spring.entity.Offre;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import tn.esprit.spring.entity.Cv.CvCandidat;
import tn.esprit.spring.entity.Entretien.Entretien;
import tn.esprit.spring.entity.User.User;





@Entity
@JsonIgnoreProperties
public class Candidature implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
    private String motivation;
	
	private String status="En Attente";
	
	private String cv;
	
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.PERSIST)
	private User users;

	@JsonIgnore
	@ManyToOne
	private Offre offres;
	
	@OneToOne(fetch=FetchType.EAGER,mappedBy="candidature") 
	@JsonIgnore
	private Entretien entretien;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<CvCandidat> cvcandidats;
	
	

	

	public Set<CvCandidat> getCvcandidats() {
		return cvcandidats;
	}

	public void setCvcandidats(Set<CvCandidat> cvcandidats) {
		this.cvcandidats = cvcandidats;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMotivation() {
		return motivation;
	}

	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public Offre getOffres() {
		return offres;
	}

	public void setOffres(Offre offres) {
		this.offres = offres;
	}
	
	
	

	public Entretien getEntretien() {
		return entretien;
	}

	public void setEntretien(Entretien entretien) {
		this.entretien = entretien;
	}

	public Candidature() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Candidature(Long id, String motivation, String status, String cv, User users, Offre offres) {
		super();
		this.id = id;
		this.motivation = motivation;
		this.status = status;
		this.cv = cv;
		this.users = users;
		this.offres = offres;
	}

	@Override
	public String toString() {
		return "Candidature [id=" + id + ", motivation=" + motivation + ", status=" + status + ", cv=" + cv + ", users="
				+ users + ", offres=" + offres + "]";
	}
	
	
	
	

}
