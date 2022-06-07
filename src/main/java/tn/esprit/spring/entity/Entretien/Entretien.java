package tn.esprit.spring.entity.Entretien;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import tn.esprit.spring.entity.Offre.Candidature;





@Entity
@JsonIgnoreProperties
public class Entretien implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private String localisation;
	private String email;
	 @Temporal (TemporalType.DATE)
	 @JsonFormat(pattern="yyyy-MM-dd")
	 private Date dateAjout;
	 @Temporal(TemporalType.TIME)
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	 private Date timeStart;
	 @Temporal(TemporalType.TIME)
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
	 private Date timeEnd;
	 
	 
	 @OneToOne
     @JsonIgnore
	 private Candidature candidature;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getLocalisation() {
		return localisation;
	}


	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}


	public Date getDateAjout() {
		return dateAjout;
	}


	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}


	


	public Date getTimeStart() {
		return timeStart;
	}


	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}


	public Date getTimeEnd() {
		return timeEnd;
	}


	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}


	public Candidature getCandidature() {
		return candidature;
	}


	public void setCandidature(Candidature candidature) {
		this.candidature = candidature;
	}
	
	


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Entretien() {
		super();
	}


	public Entretien(Long id, String name, String description, String localisation, Date dateAjout, Date timeStart,
			Date timeEnd, Candidature candidature) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.localisation = localisation;
		this.dateAjout = dateAjout;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.candidature = candidature;
	}


	@Override
	public String toString() {
		return "Entretien [id=" + id + ", name=" + name + ", description=" + description + ", localisation="
				+ localisation + ", dateAjout=" + dateAjout + ", timeStart=" + timeStart + ", timeEnd=" + timeEnd
				+ ", candidature=" + candidature + "]";
	}


	public Entretien(String name, String description, String localisation, Date dateAjout, Date timeStart,
			Date timeEnd) {
		super();
		this.name = name;
		this.description = description;
		this.localisation = localisation;
		this.dateAjout = dateAjout;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
	}


	public Entretien(String name, String email, String localisation, String description, Date dateAjout, Date timeStart,
			Date timeEnd) {
		super();
		this.name = name;
		this.email = email;
		this.localisation = localisation;
		this.description = description;
		this.dateAjout = dateAjout;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
	}


	

	
	 
	 
	 
	 
	 


	

}
