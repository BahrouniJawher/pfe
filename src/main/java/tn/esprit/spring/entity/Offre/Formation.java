package tn.esprit.spring.entity.Offre;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@DiscriminatorValue(value="formation")
public class Formation extends Offre implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String domaine;
    private String lieu;
    
    @Temporal (TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
    
    @Temporal (TemporalType.TIME)
    private Date time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Formation(Long id, String domaine, String lieu, Date date, Date time) {
		super();
		this.id = id;
		this.domaine = domaine;
		this.lieu = lieu;
		this.date = date;
		this.time = time;
	}

	public Formation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Formation(Long id, String title, String description, String adresse, String type, String nbreposte,
			Date dateAjout, Date dateExpiration, Secteur secteur) {
		super(id, title, description, adresse, type, nbreposte, dateAjout, dateExpiration, secteur);
		// TODO Auto-generated constructor stub
	}

	public Formation(Long id, String title, String description, String adresse, String type, String nbreposte,
			Date dateAjout, Date dateExpiration, String contenu, Set<ImageOffre> images, Secteur secteur) {
		super(id, title, description, adresse, type, nbreposte, dateAjout, dateExpiration, contenu, images, secteur);
		// TODO Auto-generated constructor stub
	}
    
    

}
