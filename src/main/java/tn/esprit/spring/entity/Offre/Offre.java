package tn.esprit.spring.entity.Offre;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import tn.esprit.spring.entity.User.Sexe;
import tn.esprit.spring.entity.User.User;





@Entity
@JsonIgnoreProperties
public class Offre implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7950217207447904668L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String title;
    private String description;
    private String adresse;
    private String type;
    private String nbreposte;
    @Temporal (TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateAjout;
    @Temporal (TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateExpiration;
    private String contenu;
    private String experience;
    private String langue;
    private String niveau;
    private String renumeration;
    private String genre;
    private String categorie;
    private String motcle;






   

    @JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<ImageOffre> Images;
    
    @ManyToOne
    private Secteur secteur;
    
    @JsonIgnore
    @ManyToOne
    private User offreusers;
    
    
    @JsonIgnore
	@OneToMany(mappedBy="offres",cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	public List<Candidature> candidature;
    
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNbreposte() {
		return nbreposte;
	}
	public void setNbreposte(String nbreposte) {
		this.nbreposte = nbreposte;
	}
	public Date getDateAjout() {
		return dateAjout;
	}
	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}
	public Date getDateExpiration() {
		return dateExpiration;
	}
	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	
	
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public Set<ImageOffre> getImages() {
		return Images;
	}
	public void setImages(Set<ImageOffre> images) {
		Images = images;
	}
	public Secteur getSecteur() {
		return secteur;
	}
	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}
	
	
	public List<Candidature> getCandidature() {
		return candidature;
	}
	public void setCandidature(List<Candidature> candidature) {
		this.candidature = candidature;
	}
	
	
	
	public User getOffreusers() {
		return offreusers;
	}
	public void setOffreusers(User offreusers) {
		this.offreusers = offreusers;
	}
	
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getLangue() {
		return langue;
	}
	public void setLangue(String langue) {
		this.langue = langue;
	}
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public String getRenumeration() {
		return renumeration;
	}
	public void setRenumeration(String renumeration) {
		this.renumeration = renumeration;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getMotcle() {
		return motcle;
	}
	public void setMotcle(String motcle) {
		this.motcle = motcle;
	}
	public Offre() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Offre(Long id, String title, String description, String adresse, String type, String nbreposte,
			Date dateAjout, Date dateExpiration, Secteur secteur) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.adresse = adresse;
		this.type = type;
		this.nbreposte = nbreposte;
		this.dateAjout = dateAjout;
		this.dateExpiration = dateExpiration;
		this.secteur = secteur;
	}
//	@Override
//	public String toString() {
//		return "Offre [id=" + id + ", title=" + title + ", description=" + description + ", adresse=" + adresse
//				+ ", type=" + type + ", nbreposte=" + nbreposte + ", dateAjout=" + dateAjout + ", dateExpiration="
//				+ dateExpiration + ", secteur=" + secteur + "]";
//	}
	public Offre(Long id, String title, String description, String adresse, String type, String nbreposte,
			Date dateAjout, Date dateExpiration, String contenu, Set<ImageOffre> images, Secteur secteur) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.adresse = adresse;
		this.type = type;
		this.nbreposte = nbreposte;
		this.dateAjout = dateAjout;
		this.dateExpiration = dateExpiration;
		this.contenu = contenu;
		Images = images;
		this.secteur = secteur;
	}
	public Offre(Long id, String title, String description, String adresse, String type, String nbreposte,
			Date dateAjout, Date dateExpiration, String contenu, String experience, String langue, String niveau,
			String renumeration, String genre, String categorie, String motcle, Set<ImageOffre> images) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.adresse = adresse;
		this.type = type;
		this.nbreposte = nbreposte;
		this.dateAjout = dateAjout;
		this.dateExpiration = dateExpiration;
		this.contenu = contenu;
		this.experience = experience;
		this.langue = langue;
		this.niveau = niveau;
		this.renumeration = renumeration;
		this.genre = genre;
		this.categorie = categorie;
		this.motcle = motcle;
		Images = images;
	}
	@Override
	public String toString() {
		return "Offre [id=" + id + ", title=" + title + ", description=" + description + ", adresse=" + adresse
				+ ", type=" + type + ", nbreposte=" + nbreposte + ", dateAjout=" + dateAjout + ", dateExpiration="
				+ dateExpiration + ", contenu=" + contenu + ", experience=" + experience + ", langue=" + langue
				+ ", niveau=" + niveau + ", renumeration=" + renumeration + ", genre=" + genre + ", categorie="
				+ categorie + ", motcle=" + motcle + ", Images=" + Images + "]";
	}



    
    
    



}
