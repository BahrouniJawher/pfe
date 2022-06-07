package tn.esprit.spring.entity.Offre;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.minidev.json.annotate.JsonIgnore;


@Entity
//@JsonIgnoreProperties
public class ImageOffre implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String image;
	//@ManyToOne
	//@JoinColumn
	//@JsonIgnore
	//private Offre Idoffre;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
//	public Offre getIdoffre() {
//		return Idoffre;
//	}
//	public void setIdoffre(Offre idoffre) {
//		Idoffre = idoffre;
//	}
	public ImageOffre() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ImageOffre(Long id, String image) {
		super();
		this.id = id;
		this.image = image;
		//Idoffre = idoffre;
	}
	
	

}
