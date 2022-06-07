package tn.esprit.spring.entity.Offre;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import tn.esprit.spring.entity.Cv.CvCandidat;

public class CandidatureOffreUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private long id;
	
	private long idoffre;
		
	private long iduser;
	
	private String nom;
	
	private String prenom;
	
	private String email;
	
	private String motivation;
	
	private String cv;
	
	private String title;
	
	private String description;
	
	private String nbreposte;

	private Date dateExpiration;
	
	private String status;
	
	private Set<CvCandidat> cvcandidats;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdoffre() {
		return idoffre;
	}

	public void setIdoffre(long idoffre) {
		this.idoffre = idoffre;
	}

	public long getIduser() {
		return iduser;
	}

	public void setIduser(long iduser) {
		this.iduser = iduser;
	}

	public String getMotivation() {
		return motivation;
	}

	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getNbreposte() {
		return nbreposte;
	}

	public void setNbreposte(String nbreposte) {
		this.nbreposte = nbreposte;
	}

	public Date getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CandidatureOffreUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Set<CvCandidat> getCvcandidats() {
		return cvcandidats;
	}

	public void setCvcandidats(Set<CvCandidat> cvcandidats) {
		this.cvcandidats = cvcandidats;
	}

	public CandidatureOffreUser(long id, long idoffre, String title, long iduser, String nom, String prenom, String email,
			String motivation, String cv) {
		super();
		this.id = id;
		this.idoffre = idoffre;
		this.title = title;
		this.iduser = iduser;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motivation = motivation;
		this.cv = cv;
	}
	
	

	
	@Override
	public String toString() {
		return "CandidatureOffreUser [id=" + id + ", idoffre=" + idoffre + ", iduser=" + iduser + ", nom=" + nom
				+ ", prenom=" + prenom + ", email=" + email + ", motivation=" + motivation + ", cv=" + cv + ", title="
				+ title + ", status=" + status + "]";
	}

	public CandidatureOffreUser(long id, long idoffre, long iduser, String nom, String prenom, String email,
			String motivation) {
		super();
		this.id = id;
		this.idoffre = idoffre;
		this.iduser = iduser;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motivation = motivation;
	}

	public CandidatureOffreUser(long id, long idoffre, String nom, String prenom, String email, String motivation) {
		super();
		this.id = id;
		this.idoffre = idoffre;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motivation = motivation;
	}

	public CandidatureOffreUser(long id, long iduser, String title, String description, String nbreposte,
			Date dateExpiration, String status) {
		super();
		this.id = id;
		this.iduser = iduser;
		this.title = title;
		this.description = description;
		this.nbreposte = nbreposte;
		this.dateExpiration = dateExpiration;
		this.status = status;
	}

	public CandidatureOffreUser(long id, long idoffre, long iduser, String nom, String prenom, String email,
			String motivation, String title, String description, String nbreposte, Date dateExpiration, String status,
			Set<CvCandidat> cvcandidats) {
		super();
		this.id = id;
		this.idoffre = idoffre;
		this.iduser = iduser;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motivation = motivation;
		this.title = title;
		this.description = description;
		this.nbreposte = nbreposte;
		this.dateExpiration = dateExpiration;
		this.status = status;
		this.cvcandidats = cvcandidats;
	}
	
	
	

}
