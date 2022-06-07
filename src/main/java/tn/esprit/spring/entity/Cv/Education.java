package tn.esprit.spring.entity.Cv;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.minidev.json.annotate.JsonIgnore;
import tn.esprit.spring.entity.User.User;

@Entity
@JsonIgnoreProperties
public class Education implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private Niveau niveau;
    private String diplome;
    private String universite;
    
    @Temporal (TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dated;
	
	@Temporal (TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date datef;
	
	
	@JsonIgnore
    @ManyToOne
	private User candedu;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public String getDiplome() {
		return diplome;
	}

	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}

	public String getUniversite() {
		return universite;
	}

	public void setUniversite(String universite) {
		this.universite = universite;
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

	
	public User getCandedu() {
		return candedu;
	}

	public void setCandedu(User candedu) {
		this.candedu = candedu;
	}

	public Education() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Education(Long id, Niveau niveau, String diplome, String universite, Date dated, Date datef, User candedu) {
		super();
		this.id = id;
		this.niveau = niveau;
		this.diplome = diplome;
		this.universite = universite;
		this.dated = dated;
		this.datef = datef;
		this.candedu = candedu;
	}

	@Override
	public String toString() {
		return "Education [id=" + id + ", niveau=" + niveau + ", diplome=" + diplome + ", universite=" + universite
				+ ", dated=" + dated + ", datef=" + datef + ", candedu=" + candedu + "]";
	}

	

	
	
	

}
