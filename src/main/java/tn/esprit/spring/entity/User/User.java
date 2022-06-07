package tn.esprit.spring.entity.User;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


import javax.persistence.JoinColumn;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import tn.esprit.spring.entity.Cv.CvCandidat;
import tn.esprit.spring.entity.Cv.Education;
import tn.esprit.spring.entity.Cv.Experience;
import tn.esprit.spring.entity.Cv.InformationsProfessionnelles;
import tn.esprit.spring.entity.Offre.Candidature;
import tn.esprit.spring.entity.Offre.Offre;












@Entity
@Table(name="MIDIXUSER")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Long id; 
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "prenom")
	private String prenom;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;

    @Transient
    private String passwordConfirm;
    
    @Column(name = "email")
   	private String email;
       
    @Column(name = "address")
   	private String address;
    
    @Column(name = "tel")
  	private String tel;
       
    @Temporal (TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateN;
    
    @ManyToMany(fetch = FetchType.EAGER)
   	@JoinTable(	name = "user_roles", 
   				joinColumns = @JoinColumn(name = "user_id"), 
   				inverseJoinColumns = @JoinColumn(name = "role_id"))
   	private Set<Role> roles = new HashSet<>();
    
    @Column(name = "sexe")
    @Enumerated(EnumType.STRING)
 	private Sexe sexe;
    
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;
    
    private String providerId;
    
    @Column(name = "EtatAcc")
	private Boolean EtatAcc=true;
    
    @Column(name = "enabled")
    private boolean enabled=false;
    
    @Column(name = "reset_password_token")
    private String resetPasswordToken;
    
    @Column(name = "secteur")
    @Enumerated(EnumType.STRING)
 	private SecteurEntreprise secteur;
    
	@Column(name = "entreprise")
	private String entreprise;
	
	@Column(name = "descripssion")
	private String descripssion;
	
	@OneToMany(mappedBy="userId",cascade=CascadeType.ALL)
	@JsonIgnore
	private Set<ImageUser> Images;
	
	//private String images;
	
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<CvCandidat> cvs;
	
	
	
	@JsonIgnore    
	@OneToMany( mappedBy="userinfo", cascade=CascadeType.ALL)
	private Set<InformationsProfessionnelles> infoproffessionel;
	
	@JsonIgnore    
	@OneToMany( mappedBy="candexp", cascade=CascadeType.ALL)
	private Set<Experience> expercandidat;
	
	@JsonIgnore    
	@OneToMany( mappedBy="candedu", cascade=CascadeType.ALL)
	private Set<Education> educationcandidat;
	
	@JsonIgnore
	@OneToMany(mappedBy="users" ,cascade=CascadeType.ALL)
	private  List<Candidature> candidature;
	
	@JsonIgnore    
	@OneToMany( mappedBy="offreusers", cascade=CascadeType.ALL)
	private Set<Offre> offres;


	

	public Set<CvCandidat> getCvs() {
		return cvs;
	}

	public void setCvs(Set<CvCandidat> cvs) {
		this.cvs = cvs;
	}

	public List<Candidature> getCandidature() {
		return candidature;
	}

	public void setCandidature(List<Candidature> candidature) {
		this.candidature = candidature;
	}

	public Set<Offre> getOffres() {
		return offres;
	}

	public void setOffres(Set<Offre> offres) {
		this.offres = offres;
	}

	public Set<ImageUser> getImages() {
		return Images;
	}

	public void setImages(Set<ImageUser> images) {
		Images = images;
	}

	public SecteurEntreprise getSecteur() {
		return secteur;
	}

	public void setSecteur(SecteurEntreprise secteur) {
		this.secteur = secteur;
	}

	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}

	public String getDescripssion() {
		return descripssion;
	}

	public void setDescripssion(String descripssion) {
		this.descripssion = descripssion;
	}

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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateN() {
		return dateN;
	}

	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Sexe getSexe() {
		return sexe;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}
	
	

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public AuthProvider getProvider() {
		return provider;
	}

	public void setProvider(AuthProvider provider) {
		this.provider = provider;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public Boolean getEtatAcc() {
		return EtatAcc;
	}

	public void setEtatAcc(Boolean etatAcc) {
		EtatAcc = etatAcc;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}
	
	
	

	

	public User(Long id, String nom, String prenom, String username, String password, String passwordConfirm,
			String email, String address, Date dateN, Set<Role> roles, Sexe sexe) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.email = email;
		this.address = address;
		this.dateN = dateN;
		this.roles = roles;
		this.sexe = sexe;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String email, String password, String nom, String prenom, String address,
			Date dateN, String tel, Sexe sexe) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.dateN = dateN;
		this.tel = tel;
		this.sexe = sexe;
	}
	
	
	

	public User(String username, String email, String password, String nom, String prenom, String address,
			Date dateN, String tel) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.dateN = dateN;
		this.tel = tel;

		}
	
	
	
	

	public User(Long id, String nom, String prenom, String username, String password, String passwordConfirm,
			String email, String address, String tel, Date dateN, Set<Role> roles, Sexe sexe, Boolean etatAcc,
			boolean enabled, String resetPasswordToken) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.email = email;
		this.address = address;
		this.tel = tel;
		this.dateN = dateN;
		this.roles = roles;
		this.sexe = sexe;
		EtatAcc = etatAcc;
		this.enabled = enabled;
		this.resetPasswordToken = resetPasswordToken;
	}
	
	

	public User(Long id, String email, String resetPasswordToken) {
		super();
		this.id = id;
		this.email = email;
		this.resetPasswordToken = resetPasswordToken;
	}

	public User(String username, String email, String password, String nom, String prenom, String entreprise, String address, String tel,
			String description,SecteurEntreprise secteur) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.username = username;
		this.email = email;
		this.password = password;
		this.entreprise = entreprise;
		this.address = address;
		this.tel = tel;
		this.descripssion = description;
		this.secteur = secteur;
//		EtatAcc = etatAcc;
//		this.enabled = enabled;
//		this.resetPasswordToken = resetPasswordToken;
//		this.roles = roles;


	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", password="
				+ password + ", passwordConfirm=" + passwordConfirm + ", email=" + email + ", address=" + address
				+ ", dateN=" + dateN + ", roles=" + roles + ", sexe=" + sexe + "]";
	}

	public Set<InformationsProfessionnelles> getInfoproffessionel() {
		return infoproffessionel;
	}

	public void setInfoproffessionel(Set<InformationsProfessionnelles> infoproffessionel) {
		this.infoproffessionel = infoproffessionel;
	}

	public Set<Experience> getExpercandidat() {
		return expercandidat;
	}

	public void setExpercandidat(Set<Experience> expercandidat) {
		this.expercandidat = expercandidat;
	}

	public Set<Education> getEducationcandidat() {
		return educationcandidat;
	}

	public void setEducationcandidat(Set<Education> educationcandidat) {
		this.educationcandidat = educationcandidat;
	}
    
    
    


}
