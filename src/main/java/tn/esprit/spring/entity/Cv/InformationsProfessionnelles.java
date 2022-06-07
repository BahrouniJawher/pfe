package tn.esprit.spring.entity.Cv;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.minidev.json.annotate.JsonIgnore;
import tn.esprit.spring.entity.User.User;

@Entity
@Table(name="InfoProff")
public class InformationsProfessionnelles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String competence;
    private String cv;
    
   // @Enumerated(EnumType.STRING)
 	private String langue;
    
    @ManyToOne
	@JsonIgnore
	private User userinfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompetence() {
		return competence;
	}

	public void setCompetence(String competence) {
		this.competence = competence;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}
	
	



	public User getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(User userinfo) {
		this.userinfo = userinfo;
	}

	public InformationsProfessionnelles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InformationsProfessionnelles(Long id, String competence, String langue) {
		super();
		this.id = id;
		this.competence = competence;
		this.langue = langue;
	}

	public InformationsProfessionnelles(Long id, String competence, String langue, User userinfo) {
		super();
		this.id = id;
		this.competence = competence;
		this.langue = langue;
		this.userinfo = userinfo;
	}

	@Override
	public String toString() {
		return "InformationsProfessionnelles [id=" + id + ", competence=" + competence + ", langue=" + langue
				+ ", userinfo=" + userinfo + "]";
	}

	

	
    
    

}
