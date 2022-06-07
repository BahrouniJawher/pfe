package tn.esprit.spring.entity.Cv;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import net.minidev.json.annotate.JsonIgnore;
import tn.esprit.spring.entity.User.User;

@Entity
public class Experience implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String nbannee;
    private String libelle;
    private String titreposte;
    private String description;
	private String etatp;
	
	@Temporal (TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dated;
	
	@Temporal (TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date datef;
	
	@ManyToOne
	@JsonIgnore
	private User candexp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNbannee() {
		return nbannee;
	}

	public void setNbannee(String nbannee) {
		this.nbannee = nbannee;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getTitreposte() {
		return titreposte;
	}

	public void setTitreposte(String titreposte) {
		this.titreposte = titreposte;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEtatp() {
		return etatp;
	}

	public void setEtatp(String etatp) {
		this.etatp = etatp;
	}

	public Date getDated() {
		return dated;
	}

	public void setDated(Date dated) {
		this.dated = dated;
	}

	public Date getDatef() {
		return datef;
	}

	public void setDatef(Date datef) {
		this.datef = datef;
	}

	public User getCandexp() {
		return candexp;
	}

	public void setCandexp(User candexp) {
		this.candexp = candexp;
	}

	public Experience(Long id, String nbannee, String libelle, String titreposte, String description, String etatp,
			Date dated, Date datef, User candexp) {
		super();
		this.id = id;
		this.nbannee = nbannee;
		this.libelle = libelle;
		this.titreposte = titreposte;
		this.description = description;
		this.etatp = etatp;
		this.dated = dated;
		this.datef = datef;
		this.candexp = candexp;
	}

	public Experience() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Experience [id=" + id + ", nbannee=" + nbannee + ", libelle=" + libelle + ", titreposte=" + titreposte
				+ ", description=" + description + ", etatp=" + etatp + ", dated=" + dated + ", datef=" + datef
				+ ", candexp=" + candexp + "]";
	}
	
	
	

	
	
    
    
    

}
